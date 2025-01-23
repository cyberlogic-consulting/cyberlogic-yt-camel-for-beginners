package ch.cyberlogic.camel.examples.component;

import org.apache.camel.component.file.remote.SftpComponent;

public class LocalSftp {

    public SftpComponent localSftp() {
        SftpComponent localSftp = new SftpComponent();
        return localSftp;
    }

}
