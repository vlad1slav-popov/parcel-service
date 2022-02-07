package com.api.parcelservice.config;

import com.api.parcelservice.domain.*;
import com.api.parcelservice.dto.IdDTO;
import com.api.parcelservice.entity.ParcelEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ActiveMqConfig {

    @Bean
    MappingJackson2MessageConverter mappingJackson2MessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("content-type");

        Map<String, Class<?>> typeIdMapping = new HashMap<>();
        typeIdMapping.put("addParcelRequest", AddParcelRequest.class);
        typeIdMapping.put("assignToCourRequest", AssignToCourRequest.class);
        typeIdMapping.put("cancelRequest", CancelRequest.class);
        typeIdMapping.put("changeCourParcelStatusRequest", ChangeCourParcelStatusRequest.class);
        typeIdMapping.put("changeParcelStatusRequest", ChangeParcelStatusRequest.class);
        typeIdMapping.put("errorResponse", ErrorResponse.class);
        typeIdMapping.put("updDestinationRequest", UpdDestinationRequest.class);
        typeIdMapping.put("parcelEntity", ParcelEntity.class);
        typeIdMapping.put("idDTO", IdDTO.class);
        converter.setTypeIdMappings(typeIdMapping);
        return converter;
    }

//    @Bean
//    JmsTemplate jmsTemplate() throws JMSException {
//        JmsTemplate jmsTemplate = new JmsTemplate();
////        jmsTemplate.setDefaultDestinationName("testqueue");
////        ConnectionFactory connectionFactory = new TargetConn();
////        connectionFactory.createConnection();
////        jmsTemplate.setConnectionFactory(connectionFactory);
//        return jmsTemplate;
//    }
}
