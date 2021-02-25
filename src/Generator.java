import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.exceptions.CodeGenerationException;

public class Generator {
    public String getVerificationCode(String secret) throws CodeGenerationException {
        return new DefaultCodeGenerator().generate(secret, Math.floorDiv(Time.getSystemTime(), 30));
    }
}