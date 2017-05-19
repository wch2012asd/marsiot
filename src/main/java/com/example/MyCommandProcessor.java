/**
 * @author xiangstudio
 */
package com.example;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import com.sitewhere.spi.device.event.IDeviceEventOriginator;
import com.marsiot.agent.BaseCommandProcessor;
import com.marsiot.agent.ISiteWhereEventDispatcher;
import com.marsiot.agent.MarsiotAgentException;
import com.marsiot.device.communication.protobuf.proto.Sitewhere.Device.Header;
import com.marsiot.device.communication.protobuf.proto.Sitewhere.Device.RegistrationAck;

import org.json.JSONObject;
import org.json.JSONArray;

public class MyCommandProcessor extends BaseCommandProcessor {

    private String mHardwareId;

    @Override
    public void executeStartupLogic(String hardwareId, String specificationToken,
            ISiteWhereEventDispatcher dispatcher) throws MarsiotAgentException {
        mHardwareId = hardwareId;
        sendRegistration(hardwareId, specificationToken);
    }

    @Override
    public void handleRegistrationAck(Header header, RegistrationAck ack) {
        switch (ack.getState()) {
            case NEW_REGISTRATION: 
            case ALREADY_REGISTERED: {
                System.out.print("Devide("+mHardwareId+") model("+getModelName()+") registered ok!\n\n");
                onRegistrationConfirmed(ack);
                break;
            }
            case REGISTRATION_ERROR: {
                System.out.print("Registered failed\n\n");
                break;
            }
        }
    }

    public void onRegistrationConfirmed(RegistrationAck ack) {
        try {
            sendAlert(getHardwareId(), "MESSAGE", "register ok", null); 
        } catch (MarsiotAgentException e) {
        }
    }

    public void helloWorld(String greeting, Boolean loud, IDeviceEventOriginator originator)
            throws MarsiotAgentException {
        String response = greeting + " World!";
        if (loud) {
            response = response.toUpperCase();
        }

        try {
            sendAlert(getHardwareId(), "MESSAGE", "Hello world!", null); 
        } catch (MarsiotAgentException e) {
        }

        System.out.print("hello world! greeting:" + greeting + ", load:" + loud + "\n");
        sendAck(getHardwareId(), response, originator);
    }

}


