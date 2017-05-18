package se.sveaekonomi.webpay.pmtgw;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PmtGwService {

	@POST("/webpay/rest/getreconciliationreport")
	Call<ResponseBody> getReconciliationReport(
			@Query("merchantId")String merchantId, 
			@Query("message")String message,
			@Query("mac")String mac);
	
}
