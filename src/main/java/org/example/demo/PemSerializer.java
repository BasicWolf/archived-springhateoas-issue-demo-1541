package org.example.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.io.StringWriter;
import java.security.PublicKey;

@JsonComponent
public class PemSerializer extends StdSerializer<PublicKey> {
    protected PemSerializer() {
        super(PublicKey.class);
    }

    @Override
    public void serialize(PublicKey value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        var pem = pemEncode(value.getEncoded());
        generator.writeString(pem);
    }

    private String pemEncode(byte[] value) throws IOException {
        var stringWriter = new StringWriter();
        var pemWriter = new PemWriter(stringWriter);
        pemWriter.writeObject(new PemObject("PUBLIC KEY", value));
        pemWriter.close();
        return stringWriter.toString();
    }

}
