/*    */ package okhttp3.internal.http;
/*    */ 
/*    */ import kotlin.Metadata;
/*    */ import okhttp3.MediaType;
/*    */ import okhttp3.ResponseBody;
/*    */ import okio.BufferedSource;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000$\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\030\0002\0020\001B\037\022\b\020\002\032\004\030\0010\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007¢\006\002\020\bJ\b\020\004\032\0020\005H\026J\n\020\t\032\004\030\0010\nH\026J\b\020\006\032\0020\007H\026R\016\020\004\032\0020\005X\004¢\006\002\n\000R\020\020\002\032\004\030\0010\003X\004¢\006\002\n\000R\016\020\006\032\0020\007X\004¢\006\002\n\000¨\006\013"}, d2 = {"Lokhttp3/internal/http/RealResponseBody;", "Lokhttp3/ResponseBody;", "contentTypeString", "", "contentLength", "", "source", "Lokio/BufferedSource;", "(Ljava/lang/String;JLokio/BufferedSource;)V", "contentType", "Lokhttp3/MediaType;", "okhttp"})
/*    */ public final class RealResponseBody
/*    */   extends ResponseBody
/*    */ {
/*    */   private final String contentTypeString;
/*    */   private final long contentLength;
/*    */   private final BufferedSource source;
/*    */   
/*    */   public RealResponseBody(@Nullable String contentTypeString, long contentLength, @NotNull BufferedSource source) {
/* 31 */     this.contentTypeString = contentTypeString; this.contentLength = contentLength; this.source = source;
/*    */   }
/* 33 */   public long contentLength() { return this.contentLength; }
/*    */   @Nullable
/* 35 */   public MediaType contentType() { return (this.contentTypeString != null) ? MediaType.Companion.parse(this.contentTypeString) : null; } @NotNull
/*    */   public BufferedSource source() {
/* 37 */     return this.source;
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http/RealResponseBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */