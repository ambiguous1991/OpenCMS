package com.jba.opencms.security.encoder;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

@NoArgsConstructor
public class SaltGenerator {
    public String generateSalt(){
        BytesKeyGenerator bytesKeyGenerator = KeyGenerators.secureRandom(32);
        byte[] bytes = bytesKeyGenerator.generateKey();
        return new String(bytes);
    }
}
