package moanote.backend;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodingTest {
    public class EncoderTest {
        public static void main(String[] args) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encoded = encoder.encode("1234");
            System.out.println(encoded);
        }
    }
}