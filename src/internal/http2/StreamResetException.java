/*    */ package okhttp3.internal.http2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.JvmField;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004R\020\020\002\032\0020\0038\006X\004¢\006\002\n\000¨\006\005"}, d2 = {"Lokhttp3/internal/http2/StreamResetException;", "Ljava/io/IOException;", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "(Lokhttp3/internal/http2/ErrorCode;)V", "okhttp"})
/*    */ public final class StreamResetException
/*    */   extends IOException
/*    */ {
/*    */   @JvmField
/*    */   @NotNull
/*    */   public final ErrorCode errorCode;
/*    */   
/*    */   public StreamResetException(@NotNull ErrorCode errorCode) {
/* 21 */     super("stream was reset: " + errorCode); this.errorCode = errorCode;
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/StreamResetException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */