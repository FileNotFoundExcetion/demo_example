package com.example.demo.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class RSAUtils {
    private final static String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAN8BNnMjJ3zTRjKEq+JMafP8PUbAWjOyIbxDP6KoT2fdxh7m0HhVbF0FNmCJKklTzoBhLUniVmH8pA7HynH+xu8CAwEAAQ==";
    private final static String PRIVATE_KEY = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEA3wE2cyMnfNNGMoSr4kxp8/w9RsBaM7IhvEM/oqhPZ93GHubQeFVsXQU2YIkqSVPOgGEtSeJWYfykDsfKcf7G7wIDAQABAkAkBJUfyYjCL3mCBU2hskfWoWHgQIcRHG84cgAgLGEO3ETG7EFluyaTsPaJcOIrHMckqu1Hv2ITyEo7uRrPd4+JAiEA8xouEHiCH9C9vKW+F4cl9tqia0uQVjnw1xZj3Ps3quUCIQDq1hFVIBlttavQcqm1CoSpM34i4xiQqKdoXBLMMg8JQwIhALDz5YPntBAkiXe/zVai8Wd2TbN8FVwuYLive5Fm62ZhAiAqk4ks79bsZyWxcziFlFSdALL7zESl5Y4Eg7z8vI6xVQIgFHf4kVwPrn6F3TIuYvaOKHCdaeQYuSTvrDfePhrC218=";

    public static void main(String[] args) {

        RSA rsa = new RSA(PRIVATE_KEY, PUBLIC_KEY);
        byte[] encrypt = rsa.encrypt("1652077747000", KeyType.PublicKey);
        String base64 = Base64.encode(encrypt);
        System.out.println("加密========");
        System.out.println(base64);
        //new String(byte[]); 的方式导致输出乱码，且在解密时ERROR；
        //https://blog.csdn.net/qq_38022877/article/details/88560518

        //方式一、byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
        //方式二、
        System.out.println("解密========");
        byte[] decrypt = rsa.decrypt(base64, KeyType.PrivateKey);
        String s1 = new String(decrypt, StandardCharsets.UTF_8);
        System.out.println( s1);
        System.out.println("解密2========");
        String qq="xezVpmFr/fZUfh6HQlCs9RSJUN1RC4Fu +sti4obzyulfhzQrPqggSzqwhPaGMmko19RpEAWVB3/KODk8+yZvLA ==";
        byte[] decrypt2 = rsa.decrypt(qq, KeyType.PrivateKey);
        String s2 = new String(decrypt2, StandardCharsets.UTF_8);
        System.out.println( s2);
        long end = System.currentTimeMillis();
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(end / 1000, 0, ZoneOffset.ofHours(8));
        System.out.println(localDateTime);
    }


}
