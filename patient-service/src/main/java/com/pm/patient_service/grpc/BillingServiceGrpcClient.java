package com.pm.patient_service.grpc;
import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);

    /*
    "I’m declaring a gRPC client stub named blockingStub,
    which can be used to call the BillingService methods remotely using a synchronous (blocking) style."
     */
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    // localhost:9001/BillingService/CreatePatientAccount
    // aws.grpc:123123/BillingService/CreatePatientAccount
    // This is a Spring Boot constructor-based dependency injection using @Value annotations.
    public BillingServiceGrpcClient(
            // Spring Boot uses @Value("${...}") to inject values from application.properties into variables or parameters.
            @Value("${billing.service.address:localhost}") String serverAddress ,
            @Value("${billing.service.grpc.port:9001}") int serverPort
            // Try to read the property billing.service.address from application.properties
            //If not found, use default value "localhost"
    ){
        log.info("Connecting to Billing Service GRPC at {}:{}" , serverAddress , serverPort);

        // It’s like a TCP socket connection between your Patient Service (client) and Billing Service (server).
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(serverAddress , serverPort)
                .usePlaintext().build();
        /*
        This block of code is doing two things:
            1. Creating a TCP connection (channel) to the remote BillingService (gRPC server)
            2. Creating a client stub that will use that connection to call gRPC methods
         */

        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }


    public BillingResponse createBillingAccount(String patientId , String name , String email){

        BillingRequest request = BillingRequest.newBuilder().setPatientId(patientId)
                                 .setName(name).setEmail(email).build();

        BillingResponse response = blockingStub.createBillingAccount(request);

        log.info("Recieved response from billing service via GRPC : {}" , response);

        return response;
    }
}