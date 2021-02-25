import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.exceptions.CodeGenerationException;

public class Generator {
    public static String getVerificationCode(String secret) {
        try {
            return new DefaultCodeGenerator().generate(secret, Math.floorDiv(Time.getSystemTime(), 30));
        } catch (CodeGenerationException e) {
            e.printStackTrace();
            return "Error whilst generating verificationCode";
        }
    }
}