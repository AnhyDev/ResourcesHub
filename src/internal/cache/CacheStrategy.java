/*     */ package okhttp3.internal.cache;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.CacheControl;
/*     */ import okhttp3.Headers;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.http.DatesKt;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\b\030\000 \0132\0020\001:\002\013\fB\033\b\000\022\b\020\002\032\004\030\0010\003\022\b\020\004\032\004\030\0010\005¢\006\002\020\006R\023\020\004\032\004\030\0010\005¢\006\b\n\000\032\004\b\007\020\bR\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\t\020\n¨\006\r"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy;", "", "networkRequest", "Lokhttp3/Request;", "cacheResponse", "Lokhttp3/Response;", "(Lokhttp3/Request;Lokhttp3/Response;)V", "getCacheResponse", "()Lokhttp3/Response;", "getNetworkRequest", "()Lokhttp3/Request;", "Companion", "Factory", "okhttp"})
/*     */ public final class CacheStrategy
/*     */ {
/*     */   @Nullable
/*     */   private final Request networkRequest;
/*     */   @Nullable
/*     */   private final Response cacheResponse;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   public CacheStrategy(@Nullable Request networkRequest, @Nullable Response cacheResponse) {
/*  45 */     this.networkRequest = networkRequest; this.cacheResponse = cacheResponse;
/*     */   } @Nullable
/*  47 */   public final Request getNetworkRequest() { return this.networkRequest; }
/*     */   @Nullable
/*  49 */   public final Response getCacheResponse() { return this.cacheResponse; }
/*     */    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000B\n\002\030\002\n\002\020\000\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\n\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\002\030\0002\0020\001B\037\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\b\020\006\032\004\030\0010\007¢\006\002\020\bJ\b\020\027\032\0020\003H\002J\006\020\030\032\0020\031J\b\020\032\032\0020\031H\002J\b\020\033\032\0020\003H\002J\020\020\034\032\0020\0352\006\020\004\032\0020\005H\002J\b\020\036\032\0020\035H\002R\016\020\t\032\0020\nX\016¢\006\002\n\000R\020\020\006\032\004\030\0010\007X\004¢\006\002\n\000R\020\020\013\032\004\030\0010\fX\016¢\006\002\n\000R\020\020\r\032\004\030\0010\016X\016¢\006\002\n\000R\020\020\017\032\004\030\0010\016X\016¢\006\002\n\000R\020\020\020\032\004\030\0010\fX\016¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000R\016\020\021\032\0020\003X\016¢\006\002\n\000R\024\020\004\032\0020\005X\004¢\006\b\n\000\032\004\b\022\020\023R\016\020\024\032\0020\003X\016¢\006\002\n\000R\020\020\025\032\004\030\0010\016X\016¢\006\002\n\000R\020\020\026\032\004\030\0010\fX\016¢\006\002\n\000¨\006\037"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy$Factory;", "", "nowMillis", "", "request", "Lokhttp3/Request;", "cacheResponse", "Lokhttp3/Response;", "(JLokhttp3/Request;Lokhttp3/Response;)V", "ageSeconds", "", "etag", "", "expires", "Ljava/util/Date;", "lastModified", "lastModifiedString", "receivedResponseMillis", "getRequest$okhttp", "()Lokhttp3/Request;", "sentRequestMillis", "servedDate", "servedDateString", "cacheResponseAge", "compute", "Lokhttp3/internal/cache/CacheStrategy;", "computeCandidate", "computeFreshnessLifetime", "hasConditions", "", "isFreshnessLifetimeHeuristic", "okhttp"})
/*     */   public static final class Factory {
/*  52 */     private Date servedDate; private String servedDateString; private Date lastModified; private String lastModifiedString; private Date expires; private long sentRequestMillis; public Factory(long nowMillis, @NotNull Request request, @Nullable Response cacheResponse) { this.nowMillis = nowMillis; this.request = request; this.cacheResponse = cacheResponse;
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
/*  87 */       this.ageSeconds = -1;
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
/*  98 */       if (this.cacheResponse != null) {
/*  99 */         this.sentRequestMillis = this.cacheResponse.sentRequestAtMillis();
/* 100 */         this.receivedResponseMillis = this.cacheResponse.receivedResponseAtMillis();
/* 101 */         Headers headers = this.cacheResponse.headers(); byte b; int i;
/* 102 */         for (b = 0, i = headers.size(); b < i; b++) {
/* 103 */           String fieldName = headers.name(b);
/* 104 */           String value = headers.value(b);
/*     */           
/* 106 */           if (StringsKt.equals(fieldName, "Date", true)) {
/* 107 */             this.servedDate = DatesKt.toHttpDateOrNull(value);
/* 108 */             this.servedDateString = value;
/*     */           }
/* 110 */           else if (StringsKt.equals(fieldName, "Expires", true)) {
/* 111 */             this.expires = DatesKt.toHttpDateOrNull(value);
/*     */           }
/* 113 */           else if (StringsKt.equals(fieldName, "Last-Modified", true)) {
/* 114 */             this.lastModified = DatesKt.toHttpDateOrNull(value);
/* 115 */             this.lastModifiedString = value;
/*     */           }
/* 117 */           else if (StringsKt.equals(fieldName, "ETag", true)) {
/* 118 */             this.etag = value;
/*     */           }
/* 120 */           else if (StringsKt.equals(fieldName, "Age", true)) {
/* 121 */             this.ageSeconds = Util.toNonNegativeInt(value, -1);
/*     */           } 
/*     */         } 
/*     */       }  }
/*     */     private long receivedResponseMillis;
/*     */     private String etag; private int ageSeconds; private final long nowMillis; @NotNull
/*     */     private final Request request; private final Response cacheResponse; @NotNull
/*     */     public final Request getRequest$okhttp() { return this.request; } private final boolean isFreshnessLifetimeHeuristic() { Intrinsics.checkNotNull(this.cacheResponse);
/*     */       return (this.cacheResponse.cacheControl().maxAgeSeconds() == -1 && this.expires == null); } @NotNull
/* 130 */     public final CacheStrategy compute() { CacheStrategy candidate = computeCandidate();
/*     */ 
/*     */       
/* 133 */       if (candidate.getNetworkRequest() != null && this.request.cacheControl().onlyIfCached()) {
/* 134 */         return new CacheStrategy(null, null);
/*     */       }
/*     */       
/* 137 */       return candidate; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final CacheStrategy computeCandidate() {
/* 143 */       if (this.cacheResponse == null) {
/* 144 */         return new CacheStrategy(this.request, null);
/*     */       }
/*     */ 
/*     */       
/* 148 */       if (this.request.isHttps() && this.cacheResponse.handshake() == null) {
/* 149 */         return new CacheStrategy(this.request, null);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 155 */       if (!CacheStrategy.Companion.isCacheable(this.cacheResponse, this.request)) {
/* 156 */         return new CacheStrategy(this.request, null);
/*     */       }
/*     */       
/* 159 */       CacheControl requestCaching = this.request.cacheControl();
/* 160 */       if (requestCaching.noCache() || hasConditions(this.request)) {
/* 161 */         return new CacheStrategy(this.request, null);
/*     */       }
/*     */       
/* 164 */       CacheControl responseCaching = this.cacheResponse.cacheControl();
/*     */       
/* 166 */       long ageMillis = cacheResponseAge();
/* 167 */       long freshMillis = computeFreshnessLifetime();
/*     */       
/* 169 */       if (requestCaching.maxAgeSeconds() != -1) {
/* 170 */         long l = TimeUnit.SECONDS.toMillis(requestCaching.maxAgeSeconds()); boolean bool = false; freshMillis = Math.min(freshMillis, l);
/*     */       } 
/*     */       
/* 173 */       long minFreshMillis = 0L;
/* 174 */       if (requestCaching.minFreshSeconds() != -1) {
/* 175 */         minFreshMillis = TimeUnit.SECONDS.toMillis(requestCaching.minFreshSeconds());
/*     */       }
/*     */       
/* 178 */       long maxStaleMillis = 0L;
/* 179 */       if (!responseCaching.mustRevalidate() && requestCaching.maxStaleSeconds() != -1) {
/* 180 */         maxStaleMillis = TimeUnit.SECONDS.toMillis(requestCaching.maxStaleSeconds());
/*     */       }
/*     */       
/* 183 */       if (!responseCaching.noCache() && ageMillis + minFreshMillis < freshMillis + maxStaleMillis) {
/* 184 */         Response.Builder builder = this.cacheResponse.newBuilder();
/* 185 */         if (ageMillis + minFreshMillis >= freshMillis) {
/* 186 */           builder.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
/*     */         }
/* 188 */         long oneDayMillis = 86400000L;
/* 189 */         if (ageMillis > oneDayMillis && isFreshnessLifetimeHeuristic()) {
/* 190 */           builder.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
/*     */         }
/* 192 */         return new CacheStrategy(null, builder.build());
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 197 */       String conditionName = null;
/* 198 */       String conditionValue = null;
/*     */       
/* 200 */       if (this.etag != null) {
/* 201 */         conditionName = "If-None-Match";
/* 202 */         conditionValue = this.etag;
/*     */       
/*     */       }
/* 205 */       else if (this.lastModified != null) {
/* 206 */         conditionName = "If-Modified-Since";
/* 207 */         conditionValue = this.lastModifiedString;
/*     */       
/*     */       }
/* 210 */       else if (this.servedDate != null) {
/* 211 */         conditionName = "If-Modified-Since";
/* 212 */         conditionValue = this.servedDateString;
/*     */       } else {
/*     */         
/* 215 */         return new CacheStrategy(this.request, null);
/*     */       } 
/*     */       
/* 218 */       Headers.Builder conditionalRequestHeaders = this.request.headers().newBuilder();
/* 219 */       Intrinsics.checkNotNull(conditionValue); conditionalRequestHeaders.addLenient$okhttp(conditionName, conditionValue);
/*     */       
/* 221 */       Request conditionalRequest = this.request.newBuilder()
/* 222 */         .headers(conditionalRequestHeaders.build())
/* 223 */         .build();
/* 224 */       return new CacheStrategy(conditionalRequest, this.cacheResponse);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final long computeFreshnessLifetime() {
/* 232 */       Intrinsics.checkNotNull(this.cacheResponse); CacheControl responseCaching = this.cacheResponse.cacheControl();
/* 233 */       if (responseCaching.maxAgeSeconds() != -1) {
/* 234 */         return TimeUnit.SECONDS.toMillis(responseCaching.maxAgeSeconds());
/*     */       }
/*     */       
/* 237 */       Date expires = this.expires;
/* 238 */       if (expires != null) {
/* 239 */         long servedMillis = (this.servedDate != null) ? this.servedDate.getTime() : this.receivedResponseMillis;
/* 240 */         long delta = expires.getTime() - servedMillis;
/* 241 */         return (delta > 0L) ? delta : 0L;
/*     */       } 
/*     */       
/* 244 */       if (this.lastModified != null && this.cacheResponse.request().url().query() == null) {
/*     */ 
/*     */ 
/*     */         
/* 248 */         long servedMillis = (this.servedDate != null) ? this.servedDate.getTime() : this.sentRequestMillis;
/* 249 */         Intrinsics.checkNotNull(this.lastModified); long delta = servedMillis - this.lastModified.getTime();
/* 250 */         return (delta > 0L) ? (delta / 10L) : 0L;
/*     */       } 
/*     */       
/* 253 */       return 0L;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final long cacheResponseAge() {
/* 261 */       Date servedDate = this.servedDate;
/*     */       
/* 263 */       long l1 = 0L, l2 = this.receivedResponseMillis - servedDate.getTime(); boolean bool = false; long apparentReceivedAge = (servedDate != null) ? Math.max(l1, l2) : 
/*     */         
/* 265 */         0L;
/*     */ 
/*     */ 
/*     */       
/* 269 */       l2 = TimeUnit.SECONDS.toMillis(this.ageSeconds); bool = false; long receivedAge = (this.ageSeconds != -1) ? Math.max(apparentReceivedAge, l2) : 
/*     */         
/* 271 */         apparentReceivedAge;
/*     */ 
/*     */       
/* 274 */       long responseDuration = this.receivedResponseMillis - this.sentRequestMillis;
/* 275 */       long residentDuration = this.nowMillis - this.receivedResponseMillis;
/* 276 */       return receivedAge + responseDuration + residentDuration;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final boolean hasConditions(Request request) {
/* 285 */       return (request.header("If-Modified-Since") != null || request.header("If-None-Match") != null);
/*     */     } }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\036\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\026\020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\b¨\006\t"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy$Companion;", "", "()V", "isCacheable", "", "response", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     public final boolean isCacheable(@NotNull Response response, @NotNull Request request) {
/* 293 */       Intrinsics.checkNotNullParameter(response, "response"); Intrinsics.checkNotNullParameter(request, "request"); switch (response.code()) {
/*     */         case 200:
/*     */         case 203:
/*     */         case 204:
/*     */         case 300:
/*     */         case 301:
/*     */         case 308:
/*     */         case 404:
/*     */         case 405:
/*     */         case 410:
/*     */         case 414:
/*     */         case 501:
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 302:
/*     */         case 307:
/* 313 */           if (Response.header$default(response, "Expires", null, 2, null) == null && response.cacheControl().maxAgeSeconds() == -1 && !response.cacheControl().isPublic() && !response.cacheControl().isPrivate())
/*     */           {
/*     */ 
/*     */             
/* 317 */             return false;
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 323 */           return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 328 */       return (!response.cacheControl().noStore() && !request.cacheControl().noStore());
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/cache/CacheStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */