/*     */ package okhttp3;
/*     */ import kotlin.jvm.JvmName;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\"\n\002\030\002\n\002\020\000\n\000\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\n\n\002\020\016\n\002\b\021\030\000 !2\0020\001:\002 !Bq\b\002\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\006\020\005\032\0020\006\022\006\020\007\032\0020\006\022\006\020\b\032\0020\003\022\006\020\t\032\0020\003\022\006\020\n\032\0020\003\022\006\020\013\032\0020\006\022\006\020\f\032\0020\006\022\006\020\r\032\0020\003\022\006\020\016\032\0020\003\022\006\020\017\032\0020\003\022\b\020\020\032\004\030\0010\021¢\006\002\020\022J\r\020\017\032\0020\003H\007¢\006\002\b\025J\r\020\005\032\0020\006H\007¢\006\002\b\026J\r\020\013\032\0020\006H\007¢\006\002\b\027J\r\020\f\032\0020\006H\007¢\006\002\b\030J\r\020\n\032\0020\003H\007¢\006\002\b\031J\r\020\002\032\0020\003H\007¢\006\002\b\032J\r\020\004\032\0020\003H\007¢\006\002\b\033J\r\020\016\032\0020\003H\007¢\006\002\b\034J\r\020\r\032\0020\003H\007¢\006\002\b\035J\r\020\007\032\0020\006H\007¢\006\002\b\036J\b\020\037\032\0020\021H\026R\020\020\020\032\004\030\0010\021X\016¢\006\002\n\000R\023\020\017\032\0020\0038\007¢\006\b\n\000\032\004\b\017\020\023R\021\020\b\032\0020\003¢\006\b\n\000\032\004\b\b\020\023R\021\020\t\032\0020\003¢\006\b\n\000\032\004\b\t\020\023R\023\020\005\032\0020\0068\007¢\006\b\n\000\032\004\b\005\020\024R\023\020\013\032\0020\0068\007¢\006\b\n\000\032\004\b\013\020\024R\023\020\f\032\0020\0068\007¢\006\b\n\000\032\004\b\f\020\024R\023\020\n\032\0020\0038\007¢\006\b\n\000\032\004\b\n\020\023R\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020\023R\023\020\004\032\0020\0038\007¢\006\b\n\000\032\004\b\004\020\023R\023\020\016\032\0020\0038\007¢\006\b\n\000\032\004\b\016\020\023R\023\020\r\032\0020\0038\007¢\006\b\n\000\032\004\b\r\020\023R\023\020\007\032\0020\0068\007¢\006\b\n\000\032\004\b\007\020\024¨\006\""}, d2 = {"Lokhttp3/CacheControl;", "", "noCache", "", "noStore", "maxAgeSeconds", "", "sMaxAgeSeconds", "isPrivate", "isPublic", "mustRevalidate", "maxStaleSeconds", "minFreshSeconds", "onlyIfCached", "noTransform", "immutable", "headerValue", "", "(ZZIIZZZIIZZZLjava/lang/String;)V", "()Z", "()I", "-deprecated_immutable", "-deprecated_maxAgeSeconds", "-deprecated_maxStaleSeconds", "-deprecated_minFreshSeconds", "-deprecated_mustRevalidate", "-deprecated_noCache", "-deprecated_noStore", "-deprecated_noTransform", "-deprecated_onlyIfCached", "-deprecated_sMaxAgeSeconds", "toString", "Builder", "Companion", "okhttp"})
/*     */ public final class CacheControl {
/*     */   private final boolean noCache;
/*     */   private final boolean noStore;
/*     */   private final int maxAgeSeconds;
/*     */   private final int sMaxAgeSeconds;
/*     */   private final boolean isPrivate;
/*     */   private final boolean isPublic;
/*     */   private final boolean mustRevalidate;
/*     */   private final int maxStaleSeconds;
/*     */   private final int minFreshSeconds;
/*     */   private final boolean onlyIfCached;
/*     */   private final boolean noTransform;
/*     */   private final boolean immutable;
/*     */   private String headerValue;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final CacheControl FORCE_NETWORK;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final CacheControl FORCE_CACHE;
/*     */   
/*     */   private CacheControl(boolean noCache, boolean noStore, int maxAgeSeconds, int sMaxAgeSeconds, boolean isPrivate, boolean isPublic, boolean mustRevalidate, int maxStaleSeconds, int minFreshSeconds, boolean onlyIfCached, boolean noTransform, boolean immutable, String headerValue) {
/*  28 */     this.noCache = noCache; this.noStore = noStore; this.maxAgeSeconds = maxAgeSeconds; this.sMaxAgeSeconds = sMaxAgeSeconds; this.isPrivate = isPrivate; this.isPublic = isPublic; this.mustRevalidate = mustRevalidate; this.maxStaleSeconds = maxStaleSeconds; this.minFreshSeconds = minFreshSeconds; this.onlyIfCached = onlyIfCached; this.noTransform = noTransform; this.immutable = immutable; this.headerValue = headerValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmName(name = "noCache")
/*     */   public final boolean noCache() {
/*  36 */     return this.noCache;
/*     */   } @JvmName(name = "noStore")
/*     */   public final boolean noStore() {
/*  39 */     return this.noStore;
/*     */   } @JvmName(name = "maxAgeSeconds")
/*     */   public final int maxAgeSeconds() {
/*  42 */     return this.maxAgeSeconds;
/*     */   }
/*     */ 
/*     */   
/*     */   @JvmName(name = "sMaxAgeSeconds")
/*     */   public final int sMaxAgeSeconds() {
/*  48 */     return this.sMaxAgeSeconds;
/*     */   }
/*  50 */   public final boolean isPrivate() { return this.isPrivate; }
/*  51 */   public final boolean isPublic() { return this.isPublic; }
/*     */   @JvmName(name = "mustRevalidate")
/*  53 */   public final boolean mustRevalidate() { return this.mustRevalidate; }
/*     */   @JvmName(name = "maxStaleSeconds")
/*  55 */   public final int maxStaleSeconds() { return this.maxStaleSeconds; } @JvmName(name = "minFreshSeconds")
/*     */   public final int minFreshSeconds() {
/*  57 */     return this.minFreshSeconds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmName(name = "onlyIfCached")
/*     */   public final boolean onlyIfCached() {
/*  65 */     return this.onlyIfCached;
/*     */   } @JvmName(name = "noTransform")
/*  67 */   public final boolean noTransform() { return this.noTransform; } @JvmName(name = "immutable")
/*     */   public final boolean immutable() {
/*  69 */     return this.immutable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "noCache"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_noCache")
/*     */   public final boolean -deprecated_noCache() {
/*  78 */     return this.noCache;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "noStore"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_noStore")
/*     */   public final boolean -deprecated_noStore() {
/*  85 */     return this.noStore;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "maxAgeSeconds"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_maxAgeSeconds")
/*     */   public final int -deprecated_maxAgeSeconds() {
/*  92 */     return this.maxAgeSeconds;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "sMaxAgeSeconds"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_sMaxAgeSeconds")
/*     */   public final int -deprecated_sMaxAgeSeconds() {
/*  99 */     return this.sMaxAgeSeconds;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "mustRevalidate"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_mustRevalidate")
/*     */   public final boolean -deprecated_mustRevalidate() {
/* 106 */     return this.mustRevalidate;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "maxStaleSeconds"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_maxStaleSeconds")
/*     */   public final int -deprecated_maxStaleSeconds() {
/* 113 */     return this.maxStaleSeconds;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "minFreshSeconds"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_minFreshSeconds")
/*     */   public final int -deprecated_minFreshSeconds() {
/* 120 */     return this.minFreshSeconds;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "onlyIfCached"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_onlyIfCached")
/*     */   public final boolean -deprecated_onlyIfCached() {
/* 127 */     return this.onlyIfCached;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "noTransform"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_noTransform")
/*     */   public final boolean -deprecated_noTransform() {
/* 134 */     return this.noTransform;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "immutable"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_immutable")
/*     */   public final boolean -deprecated_immutable() {
/* 141 */     return this.immutable;
/*     */   } @NotNull
/*     */   public String toString() {
/* 144 */     String result = this.headerValue;
/* 145 */     if (result == null) {
/* 146 */       boolean bool1 = false, bool2 = false; StringBuilder stringBuilder1 = new StringBuilder(); boolean bool3 = false, bool4 = false; StringBuilder $this$buildString = stringBuilder1; int $i$a$-buildString-CacheControl$toString$1 = 0;
/* 147 */       if (this.noCache) $this$buildString.append("no-cache, "); 
/* 148 */       if (this.noStore) $this$buildString.append("no-store, "); 
/* 149 */       if (this.maxAgeSeconds != -1) $this$buildString.append("max-age=").append(this.maxAgeSeconds).append(", "); 
/* 150 */       if (this.sMaxAgeSeconds != -1) $this$buildString.append("s-maxage=").append(this.sMaxAgeSeconds).append(", "); 
/* 151 */       if (this.isPrivate) $this$buildString.append("private, "); 
/* 152 */       if (this.isPublic) $this$buildString.append("public, "); 
/* 153 */       if (this.mustRevalidate) $this$buildString.append("must-revalidate, "); 
/* 154 */       if (this.maxStaleSeconds != -1) $this$buildString.append("max-stale=").append(this.maxStaleSeconds).append(", "); 
/* 155 */       if (this.minFreshSeconds != -1) $this$buildString.append("min-fresh=").append(this.minFreshSeconds).append(", "); 
/* 156 */       if (this.onlyIfCached) $this$buildString.append("only-if-cached, "); 
/* 157 */       if (this.noTransform) $this$buildString.append("no-transform, "); 
/* 158 */       if (this.immutable) $this$buildString.append("immutable, "); 
/* 159 */       StringBuilder stringBuilder2 = $this$buildString; boolean bool5 = false; if ((stringBuilder2.length() == 0)) return ""; 
/* 160 */       $this$buildString.delete($this$buildString.length() - 2, $this$buildString.length()); Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
/*     */       result = stringBuilder1.toString();
/* 162 */       this.headerValue = result;
/*     */     } 
/* 164 */     return result;
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0000\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\013\n\000\n\002\020\b\n\002\b\007\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\t\n\000\030\0002\0020\001B\005¢\006\002\020\002J\006\020\r\032\0020\016J\006\020\003\032\0020\000J\026\020\017\032\0020\0002\006\020\017\032\0020\0062\006\020\020\032\0020\021J\026\020\022\032\0020\0002\006\020\022\032\0020\0062\006\020\020\032\0020\021J\026\020\023\032\0020\0002\006\020\023\032\0020\0062\006\020\020\032\0020\021J\006\020\t\032\0020\000J\006\020\n\032\0020\000J\006\020\013\032\0020\000J\006\020\f\032\0020\000J\f\020\024\032\0020\006*\0020\025H\002R\016\020\003\032\0020\004X\016¢\006\002\n\000R\016\020\005\032\0020\006X\016¢\006\002\n\000R\016\020\007\032\0020\006X\016¢\006\002\n\000R\016\020\b\032\0020\006X\016¢\006\002\n\000R\016\020\t\032\0020\004X\016¢\006\002\n\000R\016\020\n\032\0020\004X\016¢\006\002\n\000R\016\020\013\032\0020\004X\016¢\006\002\n\000R\016\020\f\032\0020\004X\016¢\006\002\n\000¨\006\026"}, d2 = {"Lokhttp3/CacheControl$Builder;", "", "()V", "immutable", "", "maxAgeSeconds", "", "maxStaleSeconds", "minFreshSeconds", "noCache", "noStore", "noTransform", "onlyIfCached", "build", "Lokhttp3/CacheControl;", "maxAge", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "maxStale", "minFresh", "clampToInt", "", "okhttp"})
/*     */   public static final class Builder {
/*     */     private boolean noCache;
/*     */     private boolean noStore;
/* 171 */     private int maxAgeSeconds = -1;
/* 172 */     private int maxStaleSeconds = -1;
/* 173 */     private int minFreshSeconds = -1; private boolean onlyIfCached;
/*     */     private boolean noTransform;
/*     */     private boolean immutable;
/*     */     
/*     */     @NotNull
/*     */     public final Builder noCache() {
/* 179 */       Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-CacheControl$Builder$noCache$1 = 0;
/* 180 */       $this$apply.noCache = true;
/*     */       return builder1;
/*     */     } @NotNull
/*     */     public final Builder noStore() {
/* 184 */       Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-CacheControl$Builder$noStore$1 = 0;
/* 185 */       $this$apply.noStore = true;
/*     */       return builder1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public final Builder maxAge(int maxAge, @NotNull TimeUnit timeUnit) {
/* 195 */       Intrinsics.checkNotNullParameter(timeUnit, "timeUnit"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-CacheControl$Builder$maxAge$1 = 0;
/* 196 */       boolean bool3 = (maxAge >= 0) ? true : false, bool4 = false, bool5 = false; if (!bool3)
/*     */       
/*     */       { 
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
/* 417 */         int $i$a$-require-CacheControl$Builder$maxAge$1$1 = 0; String str = "maxAge < 0: " + maxAge; throw (Throwable)new IllegalArgumentException(str.toString()); }  long maxAgeSecondsLong = timeUnit.toSeconds(maxAge); $this$apply.maxAgeSeconds = $this$apply.clampToInt(maxAgeSecondsLong); return builder1; } @NotNull public final Builder maxStale(int maxStale, @NotNull TimeUnit timeUnit) { Intrinsics.checkNotNullParameter(timeUnit, "timeUnit"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-CacheControl$Builder$maxStale$1 = 0; boolean bool3 = (maxStale >= 0) ? true : false, bool4 = false, bool5 = false; if (!bool3) { int $i$a$-require-CacheControl$Builder$maxStale$1$1 = 0; String str = "maxStale < 0: " + maxStale; throw (Throwable)new IllegalArgumentException(str.toString()); }  long maxStaleSecondsLong = timeUnit.toSeconds(maxStale); $this$apply.maxStaleSeconds = $this$apply.clampToInt(maxStaleSecondsLong); return builder1; } @NotNull public final Builder minFresh(int minFresh, @NotNull TimeUnit timeUnit) { Intrinsics.checkNotNullParameter(timeUnit, "timeUnit"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-CacheControl$Builder$minFresh$1 = 0; boolean bool3 = (minFresh >= 0) ? true : false, bool4 = false, bool5 = false; if (!bool3) { int $i$a$-require-CacheControl$Builder$minFresh$1$1 = 0;
/*     */         String str = "minFresh < 0: " + minFresh;
/*     */         throw (Throwable)new IllegalArgumentException(str.toString()); }
/*     */       
/*     */       long minFreshSecondsLong = timeUnit.toSeconds(minFresh);
/*     */       $this$apply.minFreshSeconds = $this$apply.clampToInt(minFreshSecondsLong);
/*     */       return builder1; }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public final Builder onlyIfCached() {
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-CacheControl$Builder$onlyIfCached$1 = 0;
/*     */       $this$apply.onlyIfCached = true;
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder noTransform() {
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-CacheControl$Builder$noTransform$1 = 0;
/*     */       $this$apply.noTransform = true;
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder immutable() {
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-CacheControl$Builder$immutable$1 = 0;
/*     */       $this$apply.immutable = true;
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     private final int clampToInt(long $this$clampToInt) {
/*     */       return ($this$clampToInt > 2147483647L) ? Integer.MAX_VALUE : (int)$this$clampToInt;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final CacheControl build() {
/*     */       return new CacheControl(this.noCache, this.noStore, this.maxAgeSeconds, -1, false, false, false, this.maxStaleSeconds, this.minFreshSeconds, this.onlyIfCached, this.noTransform, this.immutable, null, null);
/*     */     }
/*     */   }
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   static {
/*     */     FORCE_NETWORK = (new Builder()).noCache().build();
/*     */     FORCE_CACHE = (new Builder()).onlyIfCached().maxStale(2147483647, TimeUnit.SECONDS).build();
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @NotNull
/*     */   public static final CacheControl parse(@NotNull Headers headers) {
/*     */     return Companion.parse(headers);
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000&\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\b\n\002\020\016\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\020\020\006\032\0020\0042\006\020\007\032\0020\bH\007J\036\020\t\032\0020\n*\0020\0132\006\020\f\032\0020\0132\b\b\002\020\r\032\0020\nH\002R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000R\020\020\005\032\0020\0048\006X\004¢\006\002\n\000¨\006\016"}, d2 = {"Lokhttp3/CacheControl$Companion;", "", "()V", "FORCE_CACHE", "Lokhttp3/CacheControl;", "FORCE_NETWORK", "parse", "headers", "Lokhttp3/Headers;", "indexOfElement", "", "", "characters", "startIndex", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     @JvmStatic
/*     */     @NotNull
/*     */     public final CacheControl parse(@NotNull Headers headers) {
/*     */       Intrinsics.checkNotNullParameter(headers, "headers");
/*     */       boolean noCache = false;
/*     */       boolean noStore = false;
/*     */       int maxAgeSeconds = -1;
/*     */       int sMaxAgeSeconds = -1;
/*     */       boolean isPrivate = false;
/*     */       boolean isPublic = false;
/*     */       boolean mustRevalidate = false;
/*     */       int maxStaleSeconds = -1;
/*     */       int minFreshSeconds = -1;
/*     */       boolean onlyIfCached = false;
/*     */       boolean noTransform = false;
/*     */       boolean immutable = false;
/*     */       boolean canUseHeaderValue = true;
/*     */       String headerValue = (String)null;
/*     */       byte b;
/*     */       int i;
/*     */       for (b = 0, i = headers.size(); b < i; b++) {
/*     */         String name = headers.name(b);
/*     */         String value = headers.value(b);
/*     */         if (StringsKt.equals(name, "Cache-Control", true)) {
/*     */           if (headerValue != null) {
/*     */             canUseHeaderValue = false;
/*     */           } else {
/*     */             headerValue = value;
/*     */           } 
/*     */         } else if (StringsKt.equals(name, "Pragma", true)) {
/*     */           canUseHeaderValue = false;
/*     */         } else {
/*     */           continue;
/*     */         } 
/*     */         int pos = 0;
/*     */         while (pos < value.length()) {
/*     */           int tokenStart = pos;
/*     */           pos = indexOfElement(value, "=,;", pos);
/*     */           String str1 = value;
/*     */           boolean bool = false;
/*     */           if (str1 == null)
/*     */             throw new NullPointerException("null cannot be cast to non-null type java.lang.String"); 
/*     */           Intrinsics.checkNotNullExpressionValue(str1.substring(tokenStart, pos), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*     */           str1 = str1.substring(tokenStart, pos);
/*     */           bool = false;
/*     */           if (str1 == null)
/*     */             throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence"); 
/*     */           String directive = StringsKt.trim(str1).toString();
/*     */           String parameter = null;
/*     */           if (pos == value.length() || value.charAt(pos) == ',' || value.charAt(pos) == ';') {
/*     */             pos++;
/*     */             parameter = (String)null;
/*     */           } else {
/*     */             pos++;
/*     */             pos = Util.indexOfNonWhitespace(value, pos);
/*     */             if (pos < value.length() && value.charAt(pos) == '"') {
/*     */               int parameterStart = ++pos;
/*     */               pos = StringsKt.indexOf$default(value, '"', pos, false, 4, null);
/*     */               String str = value;
/*     */               boolean bool1 = false;
/*     */               if (str == null)
/*     */                 throw new NullPointerException("null cannot be cast to non-null type java.lang.String"); 
/*     */               Intrinsics.checkNotNullExpressionValue(str.substring(parameterStart, pos), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*     */               parameter = str.substring(parameterStart, pos);
/*     */               pos++;
/*     */             } else {
/*     */               int parameterStart = pos;
/*     */               pos = indexOfElement(value, ",;", pos);
/*     */               String str = value;
/*     */               boolean bool1 = false;
/*     */               if (str == null)
/*     */                 throw new NullPointerException("null cannot be cast to non-null type java.lang.String"); 
/*     */               Intrinsics.checkNotNullExpressionValue(str.substring(parameterStart, pos), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*     */               str = str.substring(parameterStart, pos);
/*     */               bool1 = false;
/*     */               if (str == null)
/*     */                 throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence"); 
/*     */               parameter = StringsKt.trim(str).toString();
/*     */             } 
/*     */           } 
/*     */           if (StringsKt.equals("no-cache", directive, true)) {
/*     */             noCache = true;
/*     */             continue;
/*     */           } 
/*     */           if (StringsKt.equals("no-store", directive, true)) {
/*     */             noStore = true;
/*     */             continue;
/*     */           } 
/*     */           if (StringsKt.equals("max-age", directive, true)) {
/*     */             maxAgeSeconds = Util.toNonNegativeInt(parameter, -1);
/*     */             continue;
/*     */           } 
/*     */           if (StringsKt.equals("s-maxage", directive, true)) {
/*     */             sMaxAgeSeconds = Util.toNonNegativeInt(parameter, -1);
/*     */             continue;
/*     */           } 
/*     */           if (StringsKt.equals("private", directive, true)) {
/*     */             isPrivate = true;
/*     */             continue;
/*     */           } 
/*     */           if (StringsKt.equals("public", directive, true)) {
/*     */             isPublic = true;
/*     */             continue;
/*     */           } 
/*     */           if (StringsKt.equals("must-revalidate", directive, true)) {
/*     */             mustRevalidate = true;
/*     */             continue;
/*     */           } 
/*     */           if (StringsKt.equals("max-stale", directive, true)) {
/*     */             maxStaleSeconds = Util.toNonNegativeInt(parameter, 2147483647);
/*     */             continue;
/*     */           } 
/*     */           if (StringsKt.equals("min-fresh", directive, true)) {
/*     */             minFreshSeconds = Util.toNonNegativeInt(parameter, -1);
/*     */             continue;
/*     */           } 
/*     */           if (StringsKt.equals("only-if-cached", directive, true)) {
/*     */             onlyIfCached = true;
/*     */             continue;
/*     */           } 
/*     */           if (StringsKt.equals("no-transform", directive, true)) {
/*     */             noTransform = true;
/*     */             continue;
/*     */           } 
/*     */           if (StringsKt.equals("immutable", directive, true))
/*     */             immutable = true; 
/*     */         } 
/*     */         continue;
/*     */       } 
/*     */       if (!canUseHeaderValue)
/*     */         headerValue = (String)null; 
/*     */       return new CacheControl(noCache, noStore, maxAgeSeconds, sMaxAgeSeconds, isPrivate, isPublic, mustRevalidate, maxStaleSeconds, minFreshSeconds, onlyIfCached, noTransform, immutable, headerValue, null);
/*     */     }
/*     */     
/*     */     private final int indexOfElement(String $this$indexOfElement, String characters, int startIndex) {
/*     */       for (int i = startIndex, j = $this$indexOfElement.length(); i < j; i++) {
/*     */         if (StringsKt.contains$default(characters, $this$indexOfElement.charAt(i), false, 2, null))
/*     */           return i; 
/*     */       } 
/*     */       return $this$indexOfElement.length();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/CacheControl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */