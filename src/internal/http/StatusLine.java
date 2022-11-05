/*     */ package okhttp3.internal.http;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.ProtocolException;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.JvmField;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.Protocol;
/*     */ import okhttp3.Response;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\036\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\016\n\002\b\004\030\000 \n2\0020\001:\001\nB\035\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007¢\006\002\020\bJ\b\020\t\032\0020\007H\026R\020\020\004\032\0020\0058\006X\004¢\006\002\n\000R\020\020\006\032\0020\0078\006X\004¢\006\002\n\000R\020\020\002\032\0020\0038\006X\004¢\006\002\n\000¨\006\013"}, d2 = {"Lokhttp3/internal/http/StatusLine;", "", "protocol", "Lokhttp3/Protocol;", "code", "", "message", "", "(Lokhttp3/Protocol;ILjava/lang/String;)V", "toString", "Companion", "okhttp"})
/*     */ public final class StatusLine
/*     */ {
/*     */   @JvmField
/*     */   @NotNull
/*     */   public final Protocol protocol;
/*     */   @JvmField
/*     */   public final int code;
/*     */   
/*     */   public StatusLine(@NotNull Protocol protocol, int code, @NotNull String message) {
/*  24 */     this.protocol = protocol; this.code = code; this.message = message;
/*     */   }
/*     */   @JvmField
/*     */   @NotNull
/*     */   public final String message; public static final int HTTP_TEMP_REDIRECT = 307; public static final int HTTP_PERM_REDIRECT = 308; public static final int HTTP_MISDIRECTED_REQUEST = 421; public static final int HTTP_CONTINUE = 100; public static final Companion Companion = new Companion(null);
/*     */   @NotNull
/*     */   public String toString() {
/*  31 */     boolean bool1 = false, bool2 = false; StringBuilder stringBuilder1 = new StringBuilder(); boolean bool3 = false, bool4 = false; StringBuilder $this$buildString = stringBuilder1; int $i$a$-buildString-StatusLine$toString$1 = 0;
/*  32 */     if (this.protocol == Protocol.HTTP_1_0) {
/*  33 */       $this$buildString.append("HTTP/1.0");
/*     */     } else {
/*  35 */       $this$buildString.append("HTTP/1.1");
/*     */     } 
/*  37 */     $this$buildString.append(' ').append(this.code);
/*  38 */     $this$buildString.append(' ').append(this.message);
/*     */     Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
/*     */     return stringBuilder1.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\b\032\0020\t2\006\020\n\032\0020\013J\016\020\f\032\0020\t2\006\020\r\032\0020\016R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\004XT¢\006\002\n\000R\016\020\006\032\0020\004XT¢\006\002\n\000R\016\020\007\032\0020\004XT¢\006\002\n\000¨\006\017"}, d2 = {"Lokhttp3/internal/http/StatusLine$Companion;", "", "()V", "HTTP_CONTINUE", "", "HTTP_MISDIRECTED_REQUEST", "HTTP_PERM_REDIRECT", "HTTP_TEMP_REDIRECT", "get", "Lokhttp3/internal/http/StatusLine;", "response", "Lokhttp3/Response;", "parse", "statusLine", "", "okhttp"})
/*     */   public static final class Companion
/*     */   {
/*     */     private Companion() {}
/*     */     
/*     */     @NotNull
/*     */     public final StatusLine get(@NotNull Response response) {
/*  51 */       Intrinsics.checkNotNullParameter(response, "response"); return new StatusLine(response.protocol(), response.code(), response.message());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public final StatusLine parse(@NotNull String statusLine) throws IOException {
/*     */       int i;
/*  60 */       Intrinsics.checkNotNullParameter(statusLine, "statusLine"); int codeStart = 0;
/*  61 */       Protocol protocol = null;
/*  62 */       if (StringsKt.startsWith$default(statusLine, "HTTP/1.", false, 2, null)) {
/*  63 */         if (statusLine.length() < 9 || statusLine.charAt(8) != ' ') {
/*  64 */           throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
/*     */         }
/*  66 */         int httpMinorVersion = statusLine.charAt(7) - 48;
/*  67 */         codeStart = 9;
/*     */ 
/*     */         
/*  70 */         if (httpMinorVersion == 1) {
/*     */         
/*     */         } else {
/*  73 */           throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
/*     */         }  protocol = (httpMinorVersion == 0) ? Protocol.HTTP_1_0 : (Protocol)"JD-Core does not support Kotlin";
/*  75 */       } else if (StringsKt.startsWith$default(statusLine, "ICY ", false, 2, null)) {
/*     */         
/*  77 */         protocol = Protocol.HTTP_1_0;
/*  78 */         codeStart = 4;
/*     */       } else {
/*  80 */         throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
/*     */       } 
/*     */ 
/*     */       
/*  84 */       if (statusLine.length() < codeStart + 3) {
/*  85 */         throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
/*     */       }
/*     */       try {
/*  88 */         String str = statusLine; int j = codeStart + 3; boolean bool = false; Intrinsics.checkNotNullExpressionValue(str.substring(codeStart, j), "(this as java.lang.Strin…ing(startIndex, endIndex)"); i = Integer.parseInt(str.substring(codeStart, j));
/*  89 */       } catch (NumberFormatException _) {
/*  90 */         throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
/*     */       } 
/*     */       
/*     */       int code = i;
/*     */       
/*  95 */       String message = "";
/*  96 */       if (statusLine.length() > codeStart + 3) {
/*  97 */         if (statusLine.charAt(codeStart + 3) != ' ') {
/*  98 */           throw (Throwable)new ProtocolException("Unexpected status line: " + statusLine);
/*     */         }
/* 100 */         String str = statusLine; int j = codeStart + 4; boolean bool = false; Intrinsics.checkNotNullExpressionValue(str.substring(j), "(this as java.lang.String).substring(startIndex)"); message = str.substring(j);
/*     */       } 
/*     */       
/* 103 */       return new StatusLine(protocol, code, message);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http/StatusLine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */