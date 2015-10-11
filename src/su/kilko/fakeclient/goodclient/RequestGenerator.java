package su.kilko.fakeclient.goodclient;

/**
 * Created by Kosilov Nikita on 10.10.2015.
 */
interface RequestGenerator {
    Object convertRequest(Object message) throws Exception;
}
