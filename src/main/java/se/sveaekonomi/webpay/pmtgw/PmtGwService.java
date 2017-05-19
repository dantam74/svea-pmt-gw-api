package se.sveaekonomi.webpay.pmtgw;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PmtGwService {

	@FormUrlEncoded
	@POST("/webpay/rest/getreconciliationreport")
	Call<ResponseBody> getReconciliationReport(
			@Field("merchantid")String merchantId, 
			@Field("message")String message,
			@Field("mac")String mac);
	
	@FormUrlEncoded
	@POST("/webpay/rest/getpaymentmethods")
	Call<ResponseBody> getPaymentMethods(
			@Field("merchantid")String merchantId, 
			@Field("message")String message,
			@Field("mac")String mac);
	
}
