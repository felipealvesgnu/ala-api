package br.org.ala.api.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.modelmapper.module.jsr310.Jsr310ModuleConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        Jsr310ModuleConfig config = Jsr310ModuleConfig.builder()
                .dateTimePattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") // default is yyyy-MM-dd HH:mm:ss
                .datePattern("yyyy-MM-dd")
                .build();
        modelMapper.registerModule(new Jsr310Module(config));

        return modelMapper;
    }
}
