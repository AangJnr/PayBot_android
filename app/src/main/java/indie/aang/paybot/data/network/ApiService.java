package indie.aang.paybot.data.network;


import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by AangJnr on 05, December, 2018 @ 4:03 PM
 * Work Mail cibrahim@grameenfoundation.org
 * Personal mail aang.jnr@gmail.com
 */

@Singleton
public class ApiService {

    private FdpApi fdpApi;

    @Inject
    public ApiService(FdpApi _fdpApi) {
        this.fdpApi = _fdpApi;
    }




}
