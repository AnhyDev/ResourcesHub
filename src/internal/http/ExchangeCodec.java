/*    */ package okhttp3.internal.http;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import kotlin.Metadata;
/*    */ import okhttp3.Headers;
/*    */ import okhttp3.Request;
/*    */ import okhttp3.Response;
/*    */ import okhttp3.internal.connection.RealConnection;
/*    */ import okio.Sink;
/*    */ import okio.Source;
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
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000N\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\003\bf\030\000 \0342\0020\001:\001\034J\b\020\006\032\0020\007H&J\030\020\b\032\0020\t2\006\020\n\032\0020\0132\006\020\f\032\0020\rH&J\b\020\016\032\0020\007H&J\b\020\017\032\0020\007H&J\020\020\020\032\0020\0212\006\020\022\032\0020\023H&J\022\020\024\032\004\030\0010\0252\006\020\026\032\0020\027H&J\020\020\030\032\0020\r2\006\020\022\032\0020\023H&J\b\020\031\032\0020\032H&J\020\020\033\032\0020\0072\006\020\n\032\0020\013H&R\022\020\002\032\0020\003X¦\004¢\006\006\032\004\b\004\020\005¨\006\035"}, d2 = {"Lokhttp3/internal/http/ExchangeCodec;", "", "connection", "Lokhttp3/internal/connection/RealConnection;", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "cancel", "", "createRequestBody", "Lokio/Sink;", "request", "Lokhttp3/Request;", "contentLength", "", "finishRequest", "flushRequest", "openResponseBodySource", "Lokio/Source;", "response", "Lokhttp3/Response;", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "", "reportedContentLength", "trailers", "Lokhttp3/Headers;", "writeRequestHeaders", "Companion", "okhttp"})
/*    */ public interface ExchangeCodec
/*    */ {
/*    */   public static final Companion Companion = Companion.$$INSTANCE;
/*    */   public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;
/*    */   
/*    */   @NotNull
/*    */   RealConnection getConnection();
/*    */   
/*    */   @NotNull
/*    */   Sink createRequestBody(@NotNull Request paramRequest, long paramLong) throws IOException;
/*    */   
/*    */   void writeRequestHeaders(@NotNull Request paramRequest) throws IOException;
/*    */   
/*    */   void flushRequest() throws IOException;
/*    */   
/*    */   void finishRequest() throws IOException;
/*    */   
/*    */   @Nullable
/*    */   Response.Builder readResponseHeaders(boolean paramBoolean) throws IOException;
/*    */   
/*    */   long reportedContentLength(@NotNull Response paramResponse) throws IOException;
/*    */   
/*    */   @NotNull
/*    */   Source openResponseBodySource(@NotNull Response paramResponse) throws IOException;
/*    */   
/*    */   @NotNull
/*    */   Headers trailers() throws IOException;
/*    */   
/*    */   void cancel();
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004XT¢\006\002\n\000¨\006\005"}, d2 = {"Lokhttp3/internal/http/ExchangeCodec$Companion;", "", "()V", "DISCARD_STREAM_TIMEOUT_MILLIS", "", "okhttp"})
/*    */   public static final class Companion
/*    */   {
/*    */     public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;
/*    */     
/*    */     static {
/* 72 */       Companion companion = new Companion();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http/ExchangeCodec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */