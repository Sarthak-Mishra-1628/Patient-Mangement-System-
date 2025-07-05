package com.pm.billingservice.grpc;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.*;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase{

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest ,
                                     StreamObserver<billing.BillingResponse> responseObserver) {

        log.info("createBillingAccount request received {}" , billingRequest.toString());

        // Business logic - save , calculations

        BillingResponse response = BillingResponse.newBuilder().
                                   setAccountId("12345").setStatus("ACTIVE").build();

        responseObserver.onNext(response);  //  Sends the actual response
        responseObserver.onCompleted();     // This ends the stream
        /* Tells the client: “I’m done, no more data is coming.”
           If you don’t call onCompleted(), the client will wait forever.
        */
    }
}

/*
Because gRPC uses callbacks, not normal return statements.
You don’t return, you push data back to the client using:

responseObserver.onNext(response);
responseObserver.onCompleted();
 */