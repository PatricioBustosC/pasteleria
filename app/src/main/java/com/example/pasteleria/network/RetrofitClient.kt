import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.pasteleria.network.ProductoApiService

object RetrofitClient {
    private const val BASE_URL = "http://98.80.98.199:8080/"

    val instance: ProductoApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ProductoApiService::class.java)
    }
}
