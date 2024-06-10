package com.example.services.rules;

import com.example.core.exceptions.AlreadyExistsException;
import com.example.core.exceptions.NotFoundException;
import com.example.core.mernis.EUUKPSPublicSoap;
import com.example.core.utilities.constants.MessageConstants;
import com.example.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository userRepository;

    public void checkIfExistsByEmailAndUsername(String email, String username) {

        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException(MessageConstants.EMAIL_ALREADY_EXISTS.getMessage());
        } else if (userRepository.existsByUsername(username)) {
            throw new AlreadyExistsException(MessageConstants.USERNAME_ALREADY_EXISTS.getMessage());

        }

    }

    public void tcKimlikDogrula(Long TCKimlikNo, String Ad, String Soyad, Integer DogumYili) throws Exception {
        EUUKPSPublicSoap soap = new EUUKPSPublicSoap();
        boolean result = soap.TCKimlikNoDogrula(TCKimlikNo, Ad, Soyad, DogumYili);

        if (!result) {
            throw new NotFoundException(MessageConstants.IDCARD_VALIDATION_FAILED.getMessage());
        }

    }

}
