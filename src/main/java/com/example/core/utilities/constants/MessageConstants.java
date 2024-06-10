package com.example.core.utilities.constants;

public enum MessageConstants {
    LABEL("Etiket"),
    NOT_FOUND(" bulunamadı!"),
    TASK("Görev"),
    USER("Kullanıcı"),

    ADD("Ekleme işlemi başarılı!"),
    UPDATE("Güncelleme işlemi başarılı!"),
    DELETE("Silme işlemi başarılı!"),
    GET_ALL("Tüm kayıtlar listelendi!"),
    GET("Kayıt getirildi!"),
    INVALID_PASSWORD("Geçersiz Şifre"),
    USER_NOT_FOUND("Kullanıcı bulunamadı!"),
    EMAIL_ALREADY_EXISTS("Bu e-posta zaten kullanımda!"),
    USERNAME_ALREADY_EXISTS("Bu kullanıcı adı zaten kullanımda!"),

    IDCARD_VALIDATION_FAILED("TC Kimlik doğrulama başarısız!");
    private final String message;

    MessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
