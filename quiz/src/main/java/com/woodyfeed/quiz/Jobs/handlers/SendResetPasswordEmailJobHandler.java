package com.woodyfeed.quiz.Jobs.handlers;

import org.springframework.stereotype.Component;

import com.woodyfeed.quiz.User.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SendResetPasswordEmailJobHandler implements JobRequestHandler<SendResetPasswordEmailJob> {

  private final PasswordResetTokenRepository passwordResetTokenRepository;
  private final EmailService emailService;
  private final ApplicationProperties applicationProperties;
  private final SpringTemplateEngine templateEngine;

  @Override
  @Transactional
  public void run(SendResetPasswordEmailJob sendResetPasswordEmailJob) throws Exception {
    PasswordResetToken resetToken = passwordResetTokenRepository.findById(sendResetPasswordEmailJob.getTokenId())
        .orElseThrow(() -> new IllegalArgumentException("Token not found"));
    if (!resetToken.isEmailSent()) {
      sendResetPasswordEmail(resetToken.getUser(), resetToken);
    }
  }

  private void sendResetPasswordEmail(User user, PasswordResetToken token) {
    String link = applicationProperties.getBaseUrl() + "/auth/reset-password?token=" + token.getToken();
    Context thymeleafContext = new Context();
    thymeleafContext.setVariable("user", user);
    thymeleafContext.setVariable("link", link);
    String htmlBody = templateEngine.process("password-reset", thymeleafContext);
    emailService.sendHtmlMessage(List.of(user.getEmail()), "Password reset requested", htmlBody);
    token.onEmailSent();
    passwordResetTokenRepository.save(token);
  }
}
