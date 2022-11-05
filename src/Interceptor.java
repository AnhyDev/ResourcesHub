/*    */ package okhttp3;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.functions.Function1;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\bæ\001\030\000 \0072\0020\001:\002\006\007J\020\020\002\032\0020\0032\006\020\004\032\0020\005H&¨\006\b"}, d2 = {"Lokhttp3/Interceptor;", "", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Chain", "Companion", "okhttp"})
/*    */ public interface Interceptor
/*    */ {
/*    */   public static final Companion Companion = Companion.$$INSTANCE;
/*    */   
/*    */   @NotNull
/*    */   Response intercept(@NotNull Chain paramChain) throws IOException;
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\004\bf\030\0002\0020\001J\b\020\002\032\0020\003H&J\b\020\004\032\0020\005H&J\n\020\006\032\004\030\0010\007H&J\020\020\b\032\0020\t2\006\020\n\032\0020\013H&J\b\020\f\032\0020\005H&J\b\020\n\032\0020\013H&J\030\020\r\032\0020\0002\006\020\016\032\0020\0052\006\020\017\032\0020\020H&J\030\020\021\032\0020\0002\006\020\016\032\0020\0052\006\020\017\032\0020\020H&J\030\020\022\032\0020\0002\006\020\016\032\0020\0052\006\020\017\032\0020\020H&J\b\020\023\032\0020\005H&¨\006\024"}, d2 = {"Lokhttp3/Interceptor$Chain;", "", "call", "Lokhttp3/Call;", "connectTimeoutMillis", "", "connection", "Lokhttp3/Connection;", "proceed", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "readTimeoutMillis", "withConnectTimeout", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "withReadTimeout", "withWriteTimeout", "writeTimeoutMillis", "okhttp"})
/*    */   public static interface Chain
/*    */   {
/*    */     @NotNull
/*    */     Request request();
/*    */     
/*    */     @NotNull
/*    */     Response proceed(@NotNull Request param1Request) throws IOException;
/*    */     
/*    */     @Nullable
/*    */     Connection connection();
/*    */     
/*    */     @NotNull
/*    */     Call call();
/*    */     
/*    */     int connectTimeoutMillis();
/*    */     
/*    */     @NotNull
/*    */     Chain withConnectTimeout(int param1Int, @NotNull TimeUnit param1TimeUnit);
/*    */     
/*    */     int readTimeoutMillis();
/*    */     
/*    */     @NotNull
/*    */     Chain withReadTimeout(int param1Int, @NotNull TimeUnit param1TimeUnit);
/*    */     
/*    */     int writeTimeoutMillis();
/*    */     
/*    */     @NotNull
/*    */     Chain withWriteTimeout(int param1Int, @NotNull TimeUnit param1TimeUnit);
/*    */   }
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J1\020\003\032\0020\0042#\b\004\020\005\032\035\022\023\022\0210\007¢\006\f\b\b\022\b\b\t\022\004\b\b(\n\022\004\022\0020\0130\006H\nø\001\000\002\007\n\005\b20\001¨\006\f"}, d2 = {"Lokhttp3/Interceptor$Companion;", "", "()V", "invoke", "Lokhttp3/Interceptor;", "block", "Lkotlin/Function1;", "Lokhttp3/Interceptor$Chain;", "Lkotlin/ParameterName;", "name", "chain", "Lokhttp3/Response;", "okhttp"})
/*    */   public static final class Companion
/*    */   {
/*    */     static {
/* 63 */       Companion companion = new Companion();
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     @NotNull
/*    */     public final Interceptor invoke(@NotNull Function1 block) {
/* 75 */       int $i$f$invoke = 0; Intrinsics.checkNotNullParameter(block, "block"); return new Interceptor$Companion$invoke$1(block); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 3, d1 = {"\000\016\n\000\n\002\030\002\n\000\n\002\030\002\n\000\020\000\032\0020\0012\006\020\002\032\0020\003H\n¢\006\002\b\004"}, d2 = {"<anonymous>", "Lokhttp3/Response;", "it", "Lokhttp3/Interceptor$Chain;", "intercept"}) public static final class Interceptor$Companion$invoke$1 implements Interceptor { @NotNull public final Response intercept(@NotNull Interceptor.Chain it) { Intrinsics.checkNotNullParameter(it, "it"); return (Response)this.$block.invoke(it); }
/*    */ 
/*    */       
/*    */       public Interceptor$Companion$invoke$1(Function1 param2Function1) {} }
/*    */   
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Interceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */