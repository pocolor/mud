package me.pocolor.mud.core.util;

import java.io.*;

public final class Serializer {
    private Serializer() {
    }

    public static byte[] serialize(Serializable obj) throws IOException {
        try (
                var baos = new ByteArrayOutputStream();
                var ous = new ObjectOutputStream(baos);
        ) {
            ous.writeObject(obj);
            return baos.toByteArray();
        }
    }

    public static <T extends Serializable> T deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (
                var bais = new ByteArrayInputStream(bytes);
                var ois = new ObjectInputStream(bais);
        ) {
            return (T) ois.readObject();
        }
    }
}
