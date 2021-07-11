package br.com.plannic.service;

import br.com.plannic.model.Agendamento;
import br.com.plannic.model.MateriaBase;
import br.com.plannic.model.TelegramUsuario;
import br.com.plannic.model.Tutoria;
import br.com.plannic.repository.AgendamentoRepository;
import br.com.plannic.repository.MateriaBaseRepository;
import br.com.plannic.repository.TelegramUsuarioRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@EnableScheduling
public class NotificacaoService {

    private AgendamentoRepository repository;
    private TelegramUsuarioRepository telegramUsuarioRepository;
    private MateriaBaseRepository materiaBaseRepository;

    public NotificacaoService(AgendamentoRepository repository, TelegramUsuarioRepository telegramUsuarioRepository, MateriaBaseRepository materiaBaseRepository) {
        this.repository = repository;
        this.telegramUsuarioRepository = telegramUsuarioRepository;
        this.materiaBaseRepository = materiaBaseRepository;
    }

    private static Logger logger = Logger.getLogger(NotificacaoService.class);

    private static final String TOKEN = System.getenv("TOKEN");

    @Scheduled(cron = "0 * * * * *")
    public void sendMessage() {
        TelegramBot bot = new TelegramBot(TOKEN);
        logger.info("começou o fluxo do telegram");

        List<Agendamento> agendamentos = repository.findAllAgendamentosTelegram();
        logger.info("pegou os agendamentos" + agendamentos.size());

        for (Agendamento agendamento : agendamentos) {
            logger.info("ta dentro do for");
            if (agendamento.getUsuario().getTelegramUsuario().isEmpty()) {
                logger.info("Usuario não possui chat id registrado");
            } else {
                String chatId = agendamento.getUsuario().getTelegramUsuario().stream().findFirst().get().getIdTelegram();
                LocalTime horarioInicio = agendamento.getHoraInicio();
                LocalTime horarioFim = agendamento.getHoraFim();
                int idMateria = agendamento.getIdMateria();
                String nomeMateria = agendamento.getUsuario().getMaterias().stream().filter(materia -> materia.getIdMateria() == idMateria).findFirst().get().getNomeMateria();
                String message = "Você tem um agendamento de estudo para a matéria " + nomeMateria + " entre os horários " + horarioInicio + " e " + horarioFim + ".";

                SendMessage request = new SendMessage(chatId, message)
                        .parseMode(ParseMode.HTML)
                        .disableWebPagePreview(true)
                        .disableNotification(true)
                        .replyMarkup(new ForceReply());

                SendResponse sendResponse = bot.execute(request);
                logger.info("Notificação enviada com sucesso. {}" + sendResponse.message() );
            }
        }
    }

    public void sendTutoria(Tutoria tutoria) {
        TelegramBot bot = new TelegramBot(TOKEN);

        String chatId = "-1001348939559";
        int idUsuarioTutor = tutoria.getIdUsuarioTutor();
        TelegramUsuario usuarioTutor = telegramUsuarioRepository.findByIdUsuario(idUsuarioTutor);
        String chatIdTutor = usuarioTutor.getIdTelegram();
        String usernameTutor = usuarioTutor.getUsername();
        int idusuarioAluno = tutoria.getIdUsuarioAluno();
        TelegramUsuario usuarioAluno = telegramUsuarioRepository.findByIdUsuario(idusuarioAluno);
        String chatIdAluno = usuarioAluno.getIdTelegram();
        String usernameAluno = usuarioAluno.getUsername();
        int idMateriaBase = tutoria.getIdMateriaBase();
        MateriaBase materiaBase = materiaBaseRepository.findById(idMateriaBase);
        String nomeMateria = materiaBase.getMateriaBase();

        String message;

        if (usernameAluno == null || usernameAluno.isEmpty()) {
            if (usernameTutor == null || usernameTutor.isEmpty()) {
                message = "Uma nova tutoria da matéria " + nomeMateria + " foi iniciada.\n" +
                        "Tutor: <a href=\"tg://user?id=" + chatIdTutor + "\">Usuário Tutor</a>\n"+
                        "Aluno: <a href=\"tg://user?id=" + chatIdAluno + "\">Usuário Aluno</a>\n";
            } else {
                message = "Uma nova tutoria da matéria " + nomeMateria + " foi iniciada.\n" +
                        "Tutor: " + usernameTutor + "\n"+
                        "Aluno: <a href=\"tg://user?id=" + chatIdAluno + "\">Usuário Aluno</a>\n";
            }
        } else {
            if (usernameTutor == null || usernameTutor.isEmpty()) {
                message = "Uma nova tutoria da matéria " + nomeMateria + " foi iniciada.\n" +
                        "Tutor: <a href=\"tg://user?id=" + chatIdTutor + "\">Usuário Tutor</a>\n"+
                        "Aluno: @" + usernameAluno;
            } else {
                message = "Uma nova tutoria da matéria " + nomeMateria + " foi iniciada.\n" +
                        "Tutor: @" + usernameTutor + "\n"+
                        "Aluno: @" + usernameAluno;
            }
        }

        SendMessage request = new SendMessage(chatId, message)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);

        SendResponse sendResponse = bot.execute(request);
        logger.info("Notificação para Tutor. {}" + sendResponse.message() );
    }
}