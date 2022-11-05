/*     */ package okhttp3.internal.http;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.collections.MapsKt;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.Challenge;
/*     */ import okhttp3.Cookie;
/*     */ import okhttp3.CookieJar;
/*     */ import okhttp3.Headers;
/*     */ import okhttp3.HttpUrl;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.platform.Platform;
/*     */ import okio.Buffer;
/*     */ import okio.ByteString;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"\000R\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\020\002\n\002\030\002\n\000\n\002\020!\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\005\n\000\032\020\020\003\032\0020\0042\006\020\005\032\0020\006H\007\032\030\020\007\032\b\022\004\022\0020\t0\b*\0020\n2\006\020\013\032\0020\f\032\n\020\r\032\0020\004*\0020\006\032\032\020\016\032\0020\017*\0020\0202\f\020\021\032\b\022\004\022\0020\t0\022H\002\032\016\020\023\032\004\030\0010\f*\0020\020H\002\032\016\020\024\032\004\030\0010\f*\0020\020H\002\032\032\020\025\032\0020\017*\0020\0262\006\020\027\032\0020\0302\006\020\031\032\0020\n\032\f\020\032\032\0020\004*\0020\020H\002\032\024\020\033\032\0020\004*\0020\0202\006\020\034\032\0020\035H\002\"\016\020\000\032\0020\001X\004¢\006\002\n\000\"\016\020\002\032\0020\001X\004¢\006\002\n\000¨\006\036"}, d2 = {"QUOTED_STRING_DELIMITERS", "Lokio/ByteString;", "TOKEN_DELIMITERS", "hasBody", "", "response", "Lokhttp3/Response;", "parseChallenges", "", "Lokhttp3/Challenge;", "Lokhttp3/Headers;", "headerName", "", "promisesBody", "readChallengeHeader", "", "Lokio/Buffer;", "result", "", "readQuotedString", "readToken", "receiveHeaders", "Lokhttp3/CookieJar;", "url", "Lokhttp3/HttpUrl;", "headers", "skipCommasAndWhitespace", "startsWith", "prefix", "", "okhttp"})
/*     */ @JvmName(name = "HttpHeaders")
/*     */ public final class HttpHeaders
/*     */ {
/*  37 */   private static final ByteString QUOTED_STRING_DELIMITERS = ByteString.Companion.encodeUtf8("\"\\");
/*  38 */   private static final ByteString TOKEN_DELIMITERS = ByteString.Companion.encodeUtf8("\t ,=");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static final List<Challenge> parseChallenges(@NotNull Headers $this$parseChallenges, @NotNull String headerName) {
/*  60 */     Intrinsics.checkNotNullParameter($this$parseChallenges, "$this$parseChallenges"); Intrinsics.checkNotNullParameter(headerName, "headerName"); byte b = 0; List<Challenge> result = new ArrayList(); int i;
/*  61 */     for (b = 0, i = $this$parseChallenges.size(); b < i; b++) {
/*  62 */       if (StringsKt.equals(headerName, $this$parseChallenges.name(b), true)) {
/*  63 */         Buffer header = (new Buffer()).writeUtf8($this$parseChallenges.value(b));
/*     */         try {
/*  65 */           readChallengeHeader(header, result);
/*  66 */         } catch (EOFException e) {
/*  67 */           Platform.Companion.get().log("Unable to parse challenge", 5, e);
/*     */         } 
/*     */       } 
/*     */     } 
/*  71 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private static final void readChallengeHeader(Buffer $this$readChallengeHeader, List<Challenge> result) throws EOFException {
/*  76 */     String peek = (String)null;
/*     */ 
/*     */     
/*     */     while (true) {
/*  80 */       if (peek == null) {
/*  81 */         skipCommasAndWhitespace($this$readChallengeHeader);
/*  82 */         peek = readToken($this$readChallengeHeader);
/*  83 */         if (peek == null)
/*     */           return; 
/*     */       } 
/*  86 */       String schemeName = peek;
/*     */ 
/*     */       
/*  89 */       boolean commaPrefixed = skipCommasAndWhitespace($this$readChallengeHeader);
/*  90 */       peek = readToken($this$readChallengeHeader);
/*  91 */       if (peek == null) {
/*  92 */         if (!$this$readChallengeHeader.exhausted())
/*  93 */           return;  result.add(new Challenge(schemeName, MapsKt.emptyMap()));
/*     */         
/*     */         return;
/*     */       } 
/*  97 */       int eqCount = Util.skipAll($this$readChallengeHeader, (byte)61);
/*  98 */       boolean commaSuffixed = skipCommasAndWhitespace($this$readChallengeHeader);
/*     */ 
/*     */       
/* 101 */       if (!commaPrefixed && (commaSuffixed || $this$readChallengeHeader.exhausted())) {
/*     */         
/* 103 */         Intrinsics.checkNotNullExpressionValue(Collections.singletonMap(null, peek + StringsKt.repeat("=", eqCount)), "Collections.singletonMap…ek + \"=\".repeat(eqCount))"); result.add(new Challenge(schemeName, Collections.singletonMap(null, peek + StringsKt.repeat("=", eqCount))));
/* 104 */         peek = (String)null;
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 109 */       boolean bool = false; Map<Object, Object> parameters = new LinkedHashMap<>();
/* 110 */       eqCount += Util.skipAll($this$readChallengeHeader, (byte)61);
/*     */       while (true) {
/* 112 */         if (peek == null) {
/* 113 */           peek = readToken($this$readChallengeHeader);
/* 114 */           if (skipCommasAndWhitespace($this$readChallengeHeader))
/* 115 */             break;  eqCount = Util.skipAll($this$readChallengeHeader, (byte)61);
/*     */         } 
/* 117 */         if (eqCount == 0)
/* 118 */           break;  if (eqCount > 1)
/* 119 */           return;  if (skipCommasAndWhitespace($this$readChallengeHeader)) {
/*     */           return;
/*     */         }
/* 122 */         if ((startsWith($this$readChallengeHeader, (byte)34) ? readQuotedString($this$readChallengeHeader) : 
/* 123 */           readToken($this$readChallengeHeader)) != null) { Object object = startsWith($this$readChallengeHeader, (byte)34) ? readQuotedString($this$readChallengeHeader) : readToken($this$readChallengeHeader);
/*     */ 
/*     */           
/* 126 */           String replaced = (String)parameters.put(peek, object);
/* 127 */           peek = (String)null;
/* 128 */           if (replaced != null)
/* 129 */             return;  if (!skipCommasAndWhitespace($this$readChallengeHeader) && !$this$readChallengeHeader.exhausted())
/*     */             return;  continue; }  startsWith($this$readChallengeHeader, (byte)34) ? readQuotedString($this$readChallengeHeader) : readToken($this$readChallengeHeader); return;
/* 131 */       }  result.add(new Challenge(schemeName, parameters));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static final boolean skipCommasAndWhitespace(Buffer $this$skipCommasAndWhitespace) {
/* 137 */     boolean commaFound = false;
/* 138 */     while (!$this$skipCommasAndWhitespace.exhausted()) {
/* 139 */       switch ($this$skipCommasAndWhitespace.getByte(0L)) {
/*     */         
/*     */         case 44:
/* 142 */           $this$skipCommasAndWhitespace.readByte();
/* 143 */           commaFound = true;
/*     */         
/*     */         case 9:
/*     */         case 32:
/* 147 */           $this$skipCommasAndWhitespace.readByte();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 154 */     return commaFound;
/*     */   }
/*     */   private static final boolean startsWith(Buffer $this$startsWith, byte prefix) {
/* 157 */     return (!$this$startsWith.exhausted() && $this$startsWith.getByte(0L) == prefix);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String readQuotedString(Buffer $this$readQuotedString) throws EOFException {
/* 166 */     boolean bool1 = ($this$readQuotedString.readByte() == (byte)34) ? true : false, bool2 = false, bool3 = false; bool3 = false; boolean bool4 = false; if (!bool1) { boolean bool = false; String str = "Failed requirement."; throw (Throwable)new IllegalArgumentException(str.toString()); }
/* 167 */      Buffer result = new Buffer();
/*     */     while (true) {
/* 169 */       long i = $this$readQuotedString.indexOfElement(QUOTED_STRING_DELIMITERS);
/* 170 */       if (i == -1L) return null;
/*     */       
/* 172 */       if ($this$readQuotedString.getByte(i) == (byte)34) {
/* 173 */         result.write($this$readQuotedString, i);
/*     */         
/* 175 */         $this$readQuotedString.readByte();
/* 176 */         return result.readUtf8();
/*     */       } 
/*     */       
/* 179 */       if ($this$readQuotedString.size() == i + 1L) return null; 
/* 180 */       result.write($this$readQuotedString, i);
/*     */       
/* 182 */       $this$readQuotedString.readByte();
/* 183 */       result.write($this$readQuotedString, 1L);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String readToken(Buffer $this$readToken) {
/* 192 */     long tokenSize = $this$readToken.indexOfElement(TOKEN_DELIMITERS);
/* 193 */     if (tokenSize == -1L) tokenSize = $this$readToken.size();
/*     */     
/* 195 */     return 
/* 196 */       (tokenSize != 0L) ? $this$readToken.readUtf8(tokenSize) : 
/* 197 */       null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final void receiveHeaders(@NotNull CookieJar $this$receiveHeaders, @NotNull HttpUrl url, @NotNull Headers headers) {
/* 202 */     Intrinsics.checkNotNullParameter($this$receiveHeaders, "$this$receiveHeaders"); Intrinsics.checkNotNullParameter(url, "url"); Intrinsics.checkNotNullParameter(headers, "headers"); if ($this$receiveHeaders == CookieJar.NO_COOKIES)
/*     */       return; 
/* 204 */     List cookies = Cookie.Companion.parseAll(url, headers);
/* 205 */     if (cookies.isEmpty())
/*     */       return; 
/* 207 */     $this$receiveHeaders.saveFromResponse(url, cookies);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean promisesBody(@NotNull Response $this$promisesBody) {
/* 216 */     Intrinsics.checkNotNullParameter($this$promisesBody, "$this$promisesBody"); if (Intrinsics.areEqual($this$promisesBody.request().method(), "HEAD")) {
/* 217 */       return false;
/*     */     }
/*     */     
/* 220 */     int responseCode = $this$promisesBody.code();
/*     */ 
/*     */     
/* 223 */     if ((responseCode < 100 || responseCode >= 200) && responseCode != 204 && responseCode != 304) {
/* 224 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 229 */     if (Util.headersContentLength(
/* 230 */         $this$promisesBody) != -1L || StringsKt.equals("chunked", Response.header$default($this$promisesBody, "Transfer-Encoding", null, 2, null), true)) {
/* 231 */       return true;
/*     */     }
/*     */     
/* 234 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated(message = "No longer supported", level = DeprecationLevel.ERROR, replaceWith = @ReplaceWith(imports = {}, expression = "response.promisesBody()"))
/*     */   public static final boolean hasBody(@NotNull Response response) {
/* 242 */     Intrinsics.checkNotNullParameter(response, "response"); return promisesBody(response);
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http/HttpHeaders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */