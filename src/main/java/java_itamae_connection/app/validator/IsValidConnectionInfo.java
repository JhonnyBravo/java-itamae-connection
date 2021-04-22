package java_itamae_connection.app.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.function.Predicate;
import java_itamae_connection.domain.model.ConnectionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link ConnectionInfo} のバリデーションチェックを実行する。
 */
public class IsValidConnectionInfo implements Predicate<ConnectionInfo> {
  /**
   * {@link ConnectionInfo} のバリデーションチェックを実行する。
   *
   * @param cnInfo 判定対象とする {@link ConnectionInfo} を指定する。
   * @return isValid
   *         <ul>
   *         <li>true: エラーが無いことを表す。</li>
   *         <li>false: エラーが発生したことを表す。</li>
   *         </ul>
   */
  @Override
  public boolean test(ConnectionInfo cnInfo) {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    final Set<ConstraintViolation<ConnectionInfo>> resultSet = validator.validate(cnInfo);

    if (!resultSet.isEmpty()) {
      resultSet.stream().forEach(error -> {
        final String path = error.getPropertyPath().toString();
        final String message = error.getMessage();
        logger.warn("{}: {}", path, message);
      });

      return false;
    } else {
      return true;
    }
  }
}
