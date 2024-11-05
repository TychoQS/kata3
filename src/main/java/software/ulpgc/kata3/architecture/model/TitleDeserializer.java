package software.ulpgc.kata3.architecture.model;

import software.ulpgc.kata3.architecture.model.Title;

public interface TitleDeserializer {
    Title deserialize(String line);
}
