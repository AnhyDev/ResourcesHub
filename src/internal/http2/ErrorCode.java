/*    */ package okhttp3.internal.http2;
/*    */ 
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\020\n\000\n\002\020\b\n\002\b\023\b\001\030\000 \0252\b\022\004\022\0020\0000\001:\001\025B\017\b\002\022\006\020\002\032\0020\003¢\006\002\020\004R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006j\002\b\007j\002\b\bj\002\b\tj\002\b\nj\002\b\013j\002\b\fj\002\b\rj\002\b\016j\002\b\017j\002\b\020j\002\b\021j\002\b\022j\002\b\023j\002\b\024¨\006\026"}, d2 = {"Lokhttp3/internal/http2/ErrorCode;", "", "httpCode", "", "(Ljava/lang/String;II)V", "getHttpCode", "()I", "NO_ERROR", "PROTOCOL_ERROR", "INTERNAL_ERROR", "FLOW_CONTROL_ERROR", "SETTINGS_TIMEOUT", "STREAM_CLOSED", "FRAME_SIZE_ERROR", "REFUSED_STREAM", "CANCEL", "COMPRESSION_ERROR", "CONNECT_ERROR", "ENHANCE_YOUR_CALM", "INADEQUATE_SECURITY", "HTTP_1_1_REQUIRED", "Companion", "okhttp"})
/*    */ public enum ErrorCode
/*    */ {
/*    */   NO_ERROR, PROTOCOL_ERROR, INTERNAL_ERROR, FLOW_CONTROL_ERROR, SETTINGS_TIMEOUT, STREAM_CLOSED, FRAME_SIZE_ERROR, REFUSED_STREAM, CANCEL, COMPRESSION_ERROR, CONNECT_ERROR, ENHANCE_YOUR_CALM, INADEQUATE_SECURITY, HTTP_1_1_REQUIRED;
/*    */   
/*    */   public final int getHttpCode() {
/* 19 */     return this.httpCode; } ErrorCode(int httpCode) { this.httpCode = httpCode; }
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
/*    */   private final int httpCode;
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
/* 47 */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\b\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\020\020\003\032\004\030\0010\0042\006\020\005\032\0020\006¨\006\007"}, d2 = {"Lokhttp3/internal/http2/ErrorCode$Companion;", "", "()V", "fromHttp2", "Lokhttp3/internal/http2/ErrorCode;", "code", "", "okhttp"})
/*    */   public static final class Companion {
/*    */     private Companion() {}
/*    */     
/*    */     @Nullable
/*    */     public final ErrorCode fromHttp2(int code) {
/*    */       // Byte code:
/*    */       //   0: invokestatic values : ()[Lokhttp3/internal/http2/ErrorCode;
/*    */       //   3: astore_2
/*    */       //   4: iconst_0
/*    */       //   5: istore_3
/*    */       //   6: aload_2
/*    */       //   7: astore #4
/*    */       //   9: iconst_0
/*    */       //   10: istore #5
/*    */       //   12: aload #4
/*    */       //   14: astore #6
/*    */       //   16: aload #6
/*    */       //   18: arraylength
/*    */       //   19: istore #7
/*    */       //   21: iconst_0
/*    */       //   22: istore #8
/*    */       //   24: iload #8
/*    */       //   26: iload #7
/*    */       //   28: if_icmpge -> 73
/*    */       //   31: aload #6
/*    */       //   33: iload #8
/*    */       //   35: aaload
/*    */       //   36: astore #9
/*    */       //   38: aload #9
/*    */       //   40: astore #10
/*    */       //   42: iconst_0
/*    */       //   43: istore #11
/*    */       //   45: aload #10
/*    */       //   47: invokevirtual getHttpCode : ()I
/*    */       //   50: iload_1
/*    */       //   51: if_icmpne -> 58
/*    */       //   54: iconst_1
/*    */       //   55: goto -> 59
/*    */       //   58: iconst_0
/*    */       //   59: ifeq -> 67
/*    */       //   62: aload #9
/*    */       //   64: goto -> 74
/*    */       //   67: iinc #8, 1
/*    */       //   70: goto -> 24
/*    */       //   73: aconst_null
/*    */       //   74: areturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #50	-> 0
/*    */       //   #54	-> 42
/*    */       //   #50	-> 45
/*    */       //   #50	-> 59
/*    */       //   #50	-> 74
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   42	17	10	it	Lokhttp3/internal/http2/ErrorCode;
/*    */       //   45	14	11	$i$a$-find-ErrorCode$Companion$fromHttp2$1	I
/*    */       //   0	75	0	this	Lokhttp3/internal/http2/ErrorCode$Companion;
/*    */       //   0	75	1	code	I
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/ErrorCode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */