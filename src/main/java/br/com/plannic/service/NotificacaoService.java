package br.com.plannic.service;

import br.com.plannic.model.Agendamento;
import br.com.plannic.repository.AgendamentoRepository;
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

    public NotificacaoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    private static Logger logger = Logger.getLogger(NotificacaoService.class);

    private static final String TOKEN = System.getenv("TOKEN");

    @Scheduled(cron = "0 * * * * *")
    public void sendMessage() {
        TelegramBot bot = new TelegramBot(TOKEN);
        logger.info("começou o fluxo do telegram");

        List<Agendamento> agendamentos = repository.findAllAgendamentosTelegram();
        logger.info("pegou os agendamentos");

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
                String message = "Você tem um agendamento de estudo para a máteria " + nomeMateria + " entre os horários " + horarioInicio + " e " + horarioFim + ".";

                SendMessage request = new SendMessage(chatId, message)
                        .parseMode(ParseMode.HTML)
                        .disableWebPagePreview(true)
                        .disableNotification(true)
                        .replyMarkup(new ForceReply());

                SendResponse sendResponse = bot.execute(request);
                logger.info("Notificação enviada com sucesso.");
            }
        }
    }
}
