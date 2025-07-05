package com.pm.analyticsservice.kafka;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient" , groupId = "analytics-service")
    public void ConsumeEvent(byte[] event){

        try{
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            // perform any business related to analytics here

            log.info("Received Patient Event: [PatientId={} , PatientName={} , PatientEmail={} ]" , patientEvent.getPatientId() , patientEvent.getName() , patientEvent.getEmail());
        }

        catch(InvalidProtocolBufferException e){
            log.info("Error deserializing event: {}" , e.getMessage());
        }
    }
}