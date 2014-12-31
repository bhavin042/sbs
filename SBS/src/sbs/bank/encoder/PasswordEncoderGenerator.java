package sbs.bank.encoder;
 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
public class PasswordEncoderGenerator {
 
  public static void main(String[] args) {
 
	int i = 0;
	while (i < 6) {
		String[] password ={"ayilavarapu","ramesh","hirapara","bhankar","gurjar","meruva"};
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password[i]);
		System.out.println(hashedPassword);
		i++;
	}
 
  }
}