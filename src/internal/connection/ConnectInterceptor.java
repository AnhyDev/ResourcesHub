/*    */ package okhttp3.internal.connection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okhttp3.Interceptor;
/*    */ import okhttp3.Response;
/*    */ import okhttp3.internal.http.RealInterceptorChain;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\020\020\003\032\0020\0042\006\020\005\032\0020\006H\026¨\006\007"}, d2 = {"Lokhttp3/internal/connection/ConnectInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "okhttp"})
/*    */ public final class ConnectInterceptor
/*    */   implements Interceptor
/*    */ {
/*    */   public static final ConnectInterceptor INSTANCE;
/*    */   
/*    */   static {
/* 28 */     ConnectInterceptor connectInterceptor = new ConnectInterceptor();
/*    */   } @NotNull
/*    */   public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
/* 31 */     Intrinsics.checkNotNullParameter(chain, "chain"); RealInterceptorChain realChain = (RealInterceptorChain)chain;
/* 32 */     Exchange exchange = realChain.getCall$okhttp().initExchange$okhttp((RealInterceptorChain)chain);
/* 33 */     RealInterceptorChain connectedChain = RealInterceptorChain.copy$okhttp$default(realChain, 0, exchange, null, 0, 0, 0, 61, null);
/* 34 */     return connectedChain.proceed(realChain.getRequest$okhttp());
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/connection/ConnectInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */