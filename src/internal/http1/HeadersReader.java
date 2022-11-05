/*    */ package okhttp3.internal.http1;
/*    */ 
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import okhttp3.Headers;
/*    */ import okio.BufferedSource;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000(\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\002\n\002\020\t\n\002\b\003\n\002\030\002\n\000\n\002\020\016\n\002\b\002\030\000 \r2\0020\001:\001\rB\r\022\006\020\002\032\0020\003¢\006\002\020\004J\006\020\t\032\0020\nJ\006\020\013\032\0020\fR\016\020\005\032\0020\006X\016¢\006\002\n\000R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\007\020\b¨\006\016"}, d2 = {"Lokhttp3/internal/http1/HeadersReader;", "", "source", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "headerLimit", "", "getSource", "()Lokio/BufferedSource;", "readHeaders", "Lokhttp3/Headers;", "readLine", "", "Companion", "okhttp"})
/*    */ public final class HeadersReader
/*    */ {
/*    */   private long headerLimit;
/*    */   @NotNull
/*    */   private final BufferedSource source;
/*    */   private static final int HEADER_LIMIT = 262144;
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   @NotNull
/*    */   public final BufferedSource getSource() {
/* 24 */     return this.source; } public HeadersReader(@NotNull BufferedSource source) { this.source = source;
/* 25 */     this.headerLimit = 262144L; }
/*    */   
/*    */   @NotNull
/*    */   public final String readLine() {
/* 29 */     String line = this.source.readUtf8LineStrict(this.headerLimit);
/* 30 */     this.headerLimit -= line.length();
/* 31 */     return line;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public final Headers readHeaders() {
/* 36 */     Headers.Builder result = new Headers.Builder();
/*    */     while (true) {
/* 38 */       String line = readLine();
/* 39 */       String str1 = line; boolean bool = false; if ((str1.length() == 0))
/* 40 */         break;  result.addLenient$okhttp(line);
/*    */     } 
/* 42 */     return result.build();
/*    */   }
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004XT¢\006\002\n\000¨\006\005"}, d2 = {"Lokhttp3/internal/http1/HeadersReader$Companion;", "", "()V", "HEADER_LIMIT", "", "okhttp"})
/*    */   public static final class Companion {
/*    */     private Companion() {}
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http1/HeadersReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */