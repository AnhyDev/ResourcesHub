/*     */ package okhttp3;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.regex.Pattern;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.JvmStatic;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.Regex;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.internal.HostnamesKt;
/*     */ import okhttp3.internal.Util;
/*     */ import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\002\n\002\020\t\n\002\b\003\n\002\020\013\n\002\b\f\n\002\020\b\n\002\b\004\n\002\030\002\n\002\b\013\030\000 &2\0020\001:\002%&BO\b\002\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\006\020\005\032\0020\006\022\006\020\007\032\0020\003\022\006\020\b\032\0020\003\022\006\020\t\032\0020\n\022\006\020\013\032\0020\n\022\006\020\f\032\0020\n\022\006\020\r\032\0020\n¢\006\002\020\016J\r\020\007\032\0020\003H\007¢\006\002\b\022J\023\020\023\032\0020\n2\b\020\024\032\004\030\0010\001H\002J\r\020\005\032\0020\006H\007¢\006\002\b\025J\b\020\026\032\0020\027H\027J\r\020\r\032\0020\nH\007¢\006\002\b\030J\r\020\013\032\0020\nH\007¢\006\002\b\031J\016\020\032\032\0020\n2\006\020\033\032\0020\034J\r\020\002\032\0020\003H\007¢\006\002\b\035J\r\020\b\032\0020\003H\007¢\006\002\b\036J\r\020\f\032\0020\nH\007¢\006\002\b\037J\r\020\t\032\0020\nH\007¢\006\002\b J\b\020!\032\0020\003H\026J\025\020!\032\0020\0032\006\020\"\032\0020\nH\000¢\006\002\b#J\r\020\004\032\0020\003H\007¢\006\002\b$R\023\020\007\032\0020\0038\007¢\006\b\n\000\032\004\b\007\020\017R\023\020\005\032\0020\0068\007¢\006\b\n\000\032\004\b\005\020\020R\023\020\r\032\0020\n8\007¢\006\b\n\000\032\004\b\r\020\021R\023\020\013\032\0020\n8\007¢\006\b\n\000\032\004\b\013\020\021R\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020\017R\023\020\b\032\0020\0038\007¢\006\b\n\000\032\004\b\b\020\017R\023\020\f\032\0020\n8\007¢\006\b\n\000\032\004\b\f\020\021R\023\020\t\032\0020\n8\007¢\006\b\n\000\032\004\b\t\020\021R\023\020\004\032\0020\0038\007¢\006\b\n\000\032\004\b\004\020\017¨\006'"}, d2 = {"Lokhttp3/Cookie;", "", "name", "", "value", "expiresAt", "", "domain", "path", "secure", "", "httpOnly", "persistent", "hostOnly", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;ZZZZ)V", "()Ljava/lang/String;", "()J", "()Z", "-deprecated_domain", "equals", "other", "-deprecated_expiresAt", "hashCode", "", "-deprecated_hostOnly", "-deprecated_httpOnly", "matches", "url", "Lokhttp3/HttpUrl;", "-deprecated_name", "-deprecated_path", "-deprecated_persistent", "-deprecated_secure", "toString", "forObsoleteRfc2965", "toString$okhttp", "-deprecated_value", "Builder", "Companion", "okhttp"})
/*     */ public final class Cookie {
/*     */   @NotNull
/*     */   private final String name;
/*     */   @NotNull
/*     */   private final String value;
/*     */   private final long expiresAt;
/*     */   @NotNull
/*     */   private final String domain;
/*     */   @NotNull
/*     */   private final String path;
/*     */   private final boolean secure;
/*     */   private final boolean httpOnly;
/*     */   private final boolean persistent;
/*     */   private final boolean hostOnly;
/*     */   private static final Pattern YEAR_PATTERN;
/*     */   private static final Pattern MONTH_PATTERN;
/*     */   private static final Pattern DAY_OF_MONTH_PATTERN;
/*     */   private static final Pattern TIME_PATTERN;
/*     */   
/*  44 */   private Cookie(String name, String value, long expiresAt, String domain, String path, boolean secure, boolean httpOnly, boolean persistent, boolean hostOnly) { this.name = name; this.value = value; this.expiresAt = expiresAt; this.domain = domain; this.path = path; this.secure = secure; this.httpOnly = httpOnly; this.persistent = persistent; this.hostOnly = hostOnly; } @JvmName(name = "name")
/*     */   @NotNull
/*  46 */   public final String name() { return this.name; } @JvmName(name = "value")
/*     */   @NotNull
/*     */   public final String value() {
/*  49 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmName(name = "expiresAt")
/*     */   public final long expiresAt() {
/*  60 */     return this.expiresAt;
/*     */   }
/*     */   
/*     */   @JvmName(name = "domain")
/*     */   @NotNull
/*     */   public final String domain() {
/*  66 */     return this.domain;
/*     */   }
/*     */ 
/*     */   
/*     */   @JvmName(name = "path")
/*     */   @NotNull
/*     */   public final String path() {
/*  73 */     return this.path;
/*     */   } @JvmName(name = "secure")
/*     */   public final boolean secure() {
/*  76 */     return this.secure;
/*     */   }
/*     */ 
/*     */   
/*     */   @JvmName(name = "httpOnly")
/*     */   public final boolean httpOnly() {
/*  82 */     return this.httpOnly;
/*     */   } @JvmName(name = "persistent")
/*     */   public final boolean persistent() {
/*  85 */     return this.persistent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmName(name = "hostOnly")
/*     */   public final boolean hostOnly() {
/*  96 */     return this.hostOnly;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean matches(@NotNull HttpUrl url) {
/* 104 */     Intrinsics.checkNotNullParameter(url, "url"); boolean domainMatch = this.hostOnly ? 
/* 105 */       Intrinsics.areEqual(url.host(), this.domain) : 
/*     */       
/* 107 */       Companion.domainMatch(url.host(), this.domain);
/*     */     
/* 109 */     if (!domainMatch) return false;
/*     */     
/* 111 */     if (!Companion.pathMatch(url, this.path)) return false;
/*     */     
/* 113 */     return (!this.secure || url.isHttps());
/*     */   }
/*     */   
/*     */   public boolean equals(@Nullable Object other) {
/* 117 */     return (other instanceof Cookie && Intrinsics.areEqual(((Cookie)other).name, this.name) && Intrinsics.areEqual(((Cookie)other).value, this.value) && ((Cookie)other).expiresAt == this.expiresAt && Intrinsics.areEqual(((Cookie)other).domain, this.domain) && Intrinsics.areEqual(((Cookie)other).path, this.path) && ((Cookie)other).secure == this.secure && ((Cookie)other).httpOnly == this.httpOnly && ((Cookie)other).persistent == this.persistent && ((Cookie)other).hostOnly == this.hostOnly);
/*     */   }
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
/*     */   @IgnoreJRERequirement
/*     */   public int hashCode() {
/* 131 */     int result = 17;
/* 132 */     result = 31 * result + this.name.hashCode();
/* 133 */     result = 31 * result + this.value.hashCode();
/* 134 */     result = 31 * result + Long.hashCode(this.expiresAt);
/* 135 */     result = 31 * result + this.domain.hashCode();
/* 136 */     result = 31 * result + this.path.hashCode();
/* 137 */     result = 31 * result + Boolean.hashCode(this.secure);
/* 138 */     result = 31 * result + Boolean.hashCode(this.httpOnly);
/* 139 */     result = 31 * result + Boolean.hashCode(this.persistent);
/* 140 */     result = 31 * result + Boolean.hashCode(this.hostOnly);
/* 141 */     return result;
/*     */   } @NotNull
/*     */   public String toString() {
/* 144 */     return toString$okhttp(false);
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "name"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_name")
/*     */   @NotNull
/*     */   public final String -deprecated_name() {
/* 151 */     return this.name;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "value"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_value")
/*     */   @NotNull
/*     */   public final String -deprecated_value() {
/* 158 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "persistent"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_persistent")
/*     */   public final boolean -deprecated_persistent() {
/* 165 */     return this.persistent;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "expiresAt"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_expiresAt")
/*     */   public final long -deprecated_expiresAt() {
/* 172 */     return this.expiresAt;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "hostOnly"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_hostOnly")
/*     */   public final boolean -deprecated_hostOnly() {
/* 179 */     return this.hostOnly;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "domain"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_domain")
/*     */   @NotNull
/*     */   public final String -deprecated_domain() {
/* 186 */     return this.domain;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "path"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_path")
/*     */   @NotNull
/*     */   public final String -deprecated_path() {
/* 193 */     return this.path;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "httpOnly"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_httpOnly")
/*     */   public final boolean -deprecated_httpOnly() {
/* 200 */     return this.httpOnly;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "secure"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_secure")
/*     */   public final boolean -deprecated_secure() {
/* 207 */     return this.secure;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final String toString$okhttp(boolean forObsoleteRfc2965) {
/* 215 */     boolean bool1 = false, bool2 = false; StringBuilder stringBuilder1 = new StringBuilder(); boolean bool3 = false, bool4 = false; StringBuilder $this$buildString = stringBuilder1; int $i$a$-buildString-Cookie$toString$1 = 0;
/* 216 */     $this$buildString.append(this.name);
/* 217 */     $this$buildString.append('=');
/* 218 */     $this$buildString.append(this.value);
/*     */     
/* 220 */     if (this.persistent) {
/* 221 */       if (this.expiresAt == Long.MIN_VALUE) {
/* 222 */         $this$buildString.append("; max-age=0");
/*     */       } else {
/* 224 */         $this$buildString.append("; expires=").append(DatesKt.toHttpDateString(new Date(this.expiresAt)));
/*     */       } 
/*     */     }
/*     */     
/* 228 */     if (!this.hostOnly) {
/* 229 */       $this$buildString.append("; domain=");
/* 230 */       if (forObsoleteRfc2965) {
/* 231 */         $this$buildString.append(".");
/*     */       }
/* 233 */       $this$buildString.append(this.domain);
/*     */     } 
/*     */     
/* 236 */     $this$buildString.append("; path=").append(this.path);
/*     */     
/* 238 */     if (this.secure) {
/* 239 */       $this$buildString.append("; secure");
/*     */     }
/*     */     
/* 242 */     if (this.httpOnly) {
/* 243 */       $this$buildString.append("; httponly");
/*     */     }
/*     */     
/* 246 */     Intrinsics.checkNotNullExpressionValue($this$buildString.toString(), "toString()"); return $this$buildString.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\t\n\000\n\002\020\013\n\002\b\007\n\002\030\002\n\002\b\002\030\0002\0020\001B\005¢\006\002\020\002J\006\020\017\032\0020\020J\016\020\003\032\0020\0002\006\020\003\032\0020\004J\030\020\003\032\0020\0002\006\020\003\032\0020\0042\006\020\007\032\0020\bH\002J\016\020\005\032\0020\0002\006\020\005\032\0020\006J\016\020\021\032\0020\0002\006\020\003\032\0020\004J\006\020\t\032\0020\000J\016\020\n\032\0020\0002\006\020\n\032\0020\004J\016\020\013\032\0020\0002\006\020\013\032\0020\004J\006\020\r\032\0020\000J\016\020\016\032\0020\0002\006\020\016\032\0020\004R\020\020\003\032\004\030\0010\004X\016¢\006\002\n\000R\016\020\005\032\0020\006X\016¢\006\002\n\000R\016\020\007\032\0020\bX\016¢\006\002\n\000R\016\020\t\032\0020\bX\016¢\006\002\n\000R\020\020\n\032\004\030\0010\004X\016¢\006\002\n\000R\016\020\013\032\0020\004X\016¢\006\002\n\000R\016\020\f\032\0020\bX\016¢\006\002\n\000R\016\020\r\032\0020\bX\016¢\006\002\n\000R\020\020\016\032\004\030\0010\004X\016¢\006\002\n\000¨\006\022"}, d2 = {"Lokhttp3/Cookie$Builder;", "", "()V", "domain", "", "expiresAt", "", "hostOnly", "", "httpOnly", "name", "path", "persistent", "secure", "value", "build", "Lokhttp3/Cookie;", "hostOnlyDomain", "okhttp"})
/*     */   public static final class Builder
/*     */   {
/*     */     private String name;
/*     */     
/*     */     private String value;
/*     */     
/* 257 */     private long expiresAt = 253402300799999L;
/*     */     private String domain;
/* 259 */     private String path = "/"; private boolean secure;
/*     */     private boolean httpOnly;
/*     */     private boolean persistent;
/*     */     private boolean hostOnly;
/*     */     
/*     */     @NotNull
/* 265 */     public final Builder name(@NotNull String name) { Intrinsics.checkNotNullParameter(name, "name"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Cookie$Builder$name$1 = 0;
/* 266 */       String str = name; boolean bool3 = false; boolean bool = Intrinsics.areEqual(StringsKt.trim(str).toString(), name); bool3 = false; boolean bool4 = false; if (!bool)
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
/* 615 */         int $i$a$-require-Cookie$Builder$name$1$1 = 0; String str1 = "name is not trimmed"; throw (Throwable)new IllegalArgumentException(str1.toString()); }  $this$apply.name = name; return builder1; } @NotNull public final Builder value(@NotNull String value) { Intrinsics.checkNotNullParameter(value, "value"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Cookie$Builder$value$1 = 0; String str = value; boolean bool3 = false; boolean bool = Intrinsics.areEqual(StringsKt.trim(str).toString(), value); bool3 = false; boolean bool4 = false; if (!bool) { int $i$a$-require-Cookie$Builder$value$1$1 = 0; String str1 = "value is not trimmed"; throw (Throwable)new IllegalArgumentException(str1.toString()); }  $this$apply.value = value; return builder1; } @NotNull public final Builder path(@NotNull String path) { Intrinsics.checkNotNullParameter(path, "path"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Cookie$Builder$path$1 = 0; boolean bool = StringsKt.startsWith$default(path, "/", false, 2, null); boolean bool3 = false, bool4 = false; if (!bool) { int $i$a$-require-Cookie$Builder$path$1$1 = 0;
/*     */         String str = "path must start with '/'";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString()); }
/*     */       
/*     */       $this$apply.path = path;
/*     */       return builder1; }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public final Builder expiresAt(long expiresAt) {
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Cookie$Builder$expiresAt$1 = 0;
/*     */       long l = expiresAt;
/*     */       if (l <= 0L)
/*     */         l = Long.MIN_VALUE; 
/*     */       if (l > 253402300799999L)
/*     */         l = 253402300799999L; 
/*     */       $this$apply.expiresAt = l;
/*     */       $this$apply.persistent = true;
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder domain(@NotNull String domain) {
/*     */       Intrinsics.checkNotNullParameter(domain, "domain");
/*     */       return domain(domain, false);
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder hostOnlyDomain(@NotNull String domain) {
/*     */       Intrinsics.checkNotNullParameter(domain, "domain");
/*     */       return domain(domain, true);
/*     */     }
/*     */     
/*     */     private final Builder domain(String domain, boolean hostOnly) {
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Cookie$Builder$domain$1 = 0;
/*     */       if (HostnamesKt.toCanonicalHost(domain) != null) {
/*     */         String canonicalDomain = HostnamesKt.toCanonicalHost(domain);
/*     */         $this$apply.domain = canonicalDomain;
/*     */         $this$apply.hostOnly = hostOnly;
/*     */         return builder1;
/*     */       } 
/*     */       HostnamesKt.toCanonicalHost(domain);
/*     */       throw (Throwable)new IllegalArgumentException("unexpected domain: " + domain);
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder secure() {
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Cookie$Builder$secure$1 = 0;
/*     */       $this$apply.secure = true;
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder httpOnly() {
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Cookie$Builder$httpOnly$1 = 0;
/*     */       $this$apply.httpOnly = true;
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Cookie build() {
/*     */       if (this.name != null) {
/*     */         if (this.value != null) {
/*     */           if (this.domain != null)
/*     */             return new Cookie(this.name, this.value, this.expiresAt, this.domain, this.path, this.secure, this.httpOnly, this.persistent, this.hostOnly, null); 
/*     */           throw (Throwable)new NullPointerException("builder.domain == null");
/*     */         } 
/*     */         throw (Throwable)new NullPointerException("builder.value == null");
/*     */       } 
/*     */       throw (Throwable)new NullPointerException("builder.name == null");
/*     */     }
/*     */   }
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   static {
/*     */     YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
/*     */     MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
/*     */     DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
/*     */     TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @Nullable
/*     */   public static final Cookie parse(@NotNull HttpUrl url, @NotNull String setCookie) {
/*     */     return Companion.parse(url, setCookie);
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @NotNull
/*     */   public static final List<Cookie> parseAll(@NotNull HttpUrl url, @NotNull Headers headers) {
/*     */     return Companion.parseAll(url, headers);
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000L\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020\b\n\000\n\002\020\016\n\002\b\003\n\002\020\013\n\002\b\004\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\020 \n\000\n\002\030\002\n\002\b\007\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J(\020\t\032\0020\n2\006\020\013\032\0020\f2\006\020\r\032\0020\n2\006\020\016\032\0020\n2\006\020\017\032\0020\020H\002J\030\020\021\032\0020\0202\006\020\022\032\0020\f2\006\020\023\032\0020\fH\002J'\020\024\032\004\030\0010\0252\006\020\026\032\0020\0272\006\020\030\032\0020\0312\006\020\032\032\0020\fH\000¢\006\002\b\033J\032\020\024\032\004\030\0010\0252\006\020\030\032\0020\0312\006\020\032\032\0020\fH\007J\036\020\034\032\b\022\004\022\0020\0250\0352\006\020\030\032\0020\0312\006\020\036\032\0020\037H\007J\020\020 \032\0020\f2\006\020!\032\0020\fH\002J \020\"\032\0020\0272\006\020!\032\0020\f2\006\020\r\032\0020\n2\006\020\016\032\0020\nH\002J\020\020#\032\0020\0272\006\020!\032\0020\fH\002J\030\020$\032\0020\0202\006\020\030\032\0020\0312\006\020%\032\0020\fH\002R\026\020\003\032\n \005*\004\030\0010\0040\004X\004¢\006\002\n\000R\026\020\006\032\n \005*\004\030\0010\0040\004X\004¢\006\002\n\000R\026\020\007\032\n \005*\004\030\0010\0040\004X\004¢\006\002\n\000R\026\020\b\032\n \005*\004\030\0010\0040\004X\004¢\006\002\n\000¨\006&"}, d2 = {"Lokhttp3/Cookie$Companion;", "", "()V", "DAY_OF_MONTH_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "MONTH_PATTERN", "TIME_PATTERN", "YEAR_PATTERN", "dateCharacterOffset", "", "input", "", "pos", "limit", "invert", "", "domainMatch", "urlHost", "domain", "parse", "Lokhttp3/Cookie;", "currentTimeMillis", "", "url", "Lokhttp3/HttpUrl;", "setCookie", "parse$okhttp", "parseAll", "", "headers", "Lokhttp3/Headers;", "parseDomain", "s", "parseExpires", "parseMaxAge", "pathMatch", "path", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     private final boolean domainMatch(String urlHost, String domain) {
/*     */       if (Intrinsics.areEqual(urlHost, domain))
/*     */         return true; 
/*     */       return (StringsKt.endsWith$default(urlHost, domain, false, 2, null) && urlHost.charAt(urlHost.length() - domain.length() - 1) == '.' && !Util.canParseAsIpAddress(urlHost));
/*     */     }
/*     */     
/*     */     private final boolean pathMatch(HttpUrl url, String path) {
/*     */       String urlPath = url.encodedPath();
/*     */       if (Intrinsics.areEqual(urlPath, path))
/*     */         return true; 
/*     */       if (StringsKt.startsWith$default(urlPath, path, false, 2, null)) {
/*     */         if (StringsKt.endsWith$default(path, "/", false, 2, null))
/*     */           return true; 
/*     */         if (urlPath.charAt(path.length()) == '/')
/*     */           return true; 
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @Nullable
/*     */     public final Cookie parse(@NotNull HttpUrl url, @NotNull String setCookie) {
/*     */       Intrinsics.checkNotNullParameter(url, "url");
/*     */       Intrinsics.checkNotNullParameter(setCookie, "setCookie");
/*     */       return parse$okhttp(System.currentTimeMillis(), url, setCookie);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public final Cookie parse$okhttp(long currentTimeMillis, @NotNull HttpUrl url, @NotNull String setCookie) {
/*     */       Intrinsics.checkNotNullParameter(url, "url");
/*     */       Intrinsics.checkNotNullParameter(setCookie, "setCookie");
/*     */       int cookiePairEnd = Util.delimiterOffset$default(setCookie, ';', 0, 0, 6, null);
/*     */       int pairEqualsSign = Util.delimiterOffset$default(setCookie, '=', 0, cookiePairEnd, 2, null);
/*     */       if (pairEqualsSign == cookiePairEnd)
/*     */         return null; 
/*     */       String cookieName = Util.trimSubstring$default(setCookie, 0, pairEqualsSign, 1, null);
/*     */       CharSequence charSequence = cookieName;
/*     */       boolean bool = false;
/*     */       if (((charSequence.length() == 0)) || Util.indexOfControlOrNonAscii(cookieName) != -1)
/*     */         return null; 
/*     */       String cookieValue = Util.trimSubstring(setCookie, pairEqualsSign + 1, cookiePairEnd);
/*     */       if (Util.indexOfControlOrNonAscii(cookieValue) != -1)
/*     */         return null; 
/*     */       long expiresAt = 253402300799999L;
/*     */       long deltaSeconds = -1L;
/*     */       String domain = (String)null;
/*     */       String path = (String)null;
/*     */       boolean secureOnly = false;
/*     */       boolean httpOnly = false;
/*     */       boolean hostOnly = true;
/*     */       boolean persistent = false;
/*     */       int pos = cookiePairEnd + 1;
/*     */       int limit = setCookie.length();
/*     */       while (pos < limit) {
/*     */         int attributePairEnd = Util.delimiterOffset(setCookie, ';', pos, limit);
/*     */         int attributeEqualsSign = Util.delimiterOffset(setCookie, '=', pos, attributePairEnd);
/*     */         String attributeName = Util.trimSubstring(setCookie, pos, attributeEqualsSign);
/*     */         String attributeValue = (attributeEqualsSign < attributePairEnd) ? Util.trimSubstring(setCookie, attributeEqualsSign + 1, attributePairEnd) : "";
/*     */         if (StringsKt.equals(attributeName, "expires", true)) {
/*     */           try {
/*     */             expiresAt = parseExpires(attributeValue, 0, attributeValue.length());
/*     */             persistent = true;
/*     */           } catch (IllegalArgumentException illegalArgumentException) {}
/*     */         } else if (StringsKt.equals(attributeName, "max-age", true)) {
/*     */           try {
/*     */             deltaSeconds = parseMaxAge(attributeValue);
/*     */             persistent = true;
/*     */           } catch (NumberFormatException numberFormatException) {}
/*     */         } else if (StringsKt.equals(attributeName, "domain", true)) {
/*     */           try {
/*     */             domain = parseDomain(attributeValue);
/*     */             hostOnly = false;
/*     */           } catch (IllegalArgumentException illegalArgumentException) {}
/*     */         } else if (StringsKt.equals(attributeName, "path", true)) {
/*     */           path = attributeValue;
/*     */         } else if (StringsKt.equals(attributeName, "secure", true)) {
/*     */           secureOnly = true;
/*     */         } else if (StringsKt.equals(attributeName, "httponly", true)) {
/*     */           httpOnly = true;
/*     */         } 
/*     */         pos = attributePairEnd + 1;
/*     */       } 
/*     */       if (deltaSeconds == Long.MIN_VALUE) {
/*     */         expiresAt = Long.MIN_VALUE;
/*     */       } else if (deltaSeconds != -1L) {
/*     */         long deltaMilliseconds = (deltaSeconds <= 9223372036854775L) ? (deltaSeconds * 1000L) : Long.MAX_VALUE;
/*     */         expiresAt = currentTimeMillis + deltaMilliseconds;
/*     */         if (expiresAt < currentTimeMillis || expiresAt > 253402300799999L)
/*     */           expiresAt = 253402300799999L; 
/*     */       } 
/*     */       String urlHost = url.host();
/*     */       if (domain == null) {
/*     */         domain = urlHost;
/*     */       } else if (!domainMatch(urlHost, domain)) {
/*     */         return null;
/*     */       } 
/*     */       if (urlHost.length() != domain.length() && PublicSuffixDatabase.Companion.get().getEffectiveTldPlusOne(domain) == null)
/*     */         return null; 
/*     */       if (path == null || !StringsKt.startsWith$default(path, "/", false, 2, null)) {
/*     */         String encodedPath = url.encodedPath();
/*     */         int lastSlash = StringsKt.lastIndexOf$default(encodedPath, '/', 0, false, 6, null);
/*     */         if (lastSlash != 0) {
/*     */           String str = encodedPath;
/*     */           boolean bool1 = false, bool2 = false;
/*     */           if (str == null)
/*     */             throw new NullPointerException("null cannot be cast to non-null type java.lang.String"); 
/*     */           Intrinsics.checkNotNullExpressionValue(str.substring(bool1, lastSlash), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*     */         } else {
/*     */         
/*     */         } 
/*     */         path = "/";
/*     */       } 
/*     */       return new Cookie(cookieName, cookieValue, expiresAt, domain, path, secureOnly, httpOnly, persistent, hostOnly, null);
/*     */     }
/*     */     
/*     */     private final long parseExpires(String s, int pos, int limit) {
/*     */       // Byte code:
/*     */       //   0: iload_2
/*     */       //   1: istore #4
/*     */       //   3: aload_0
/*     */       //   4: checkcast okhttp3/Cookie$Companion
/*     */       //   7: aload_1
/*     */       //   8: iload #4
/*     */       //   10: iload_3
/*     */       //   11: iconst_0
/*     */       //   12: invokespecial dateCharacterOffset : (Ljava/lang/String;IIZ)I
/*     */       //   15: istore #4
/*     */       //   17: iconst_m1
/*     */       //   18: istore #5
/*     */       //   20: iconst_m1
/*     */       //   21: istore #6
/*     */       //   23: iconst_m1
/*     */       //   24: istore #7
/*     */       //   26: iconst_m1
/*     */       //   27: istore #8
/*     */       //   29: iconst_m1
/*     */       //   30: istore #9
/*     */       //   32: iconst_m1
/*     */       //   33: istore #10
/*     */       //   35: invokestatic access$getTIME_PATTERN$cp : ()Ljava/util/regex/Pattern;
/*     */       //   38: aload_1
/*     */       //   39: checkcast java/lang/CharSequence
/*     */       //   42: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
/*     */       //   45: astore #11
/*     */       //   47: iload #4
/*     */       //   49: iload_3
/*     */       //   50: if_icmpge -> 400
/*     */       //   53: aload_0
/*     */       //   54: checkcast okhttp3/Cookie$Companion
/*     */       //   57: aload_1
/*     */       //   58: iload #4
/*     */       //   60: iconst_1
/*     */       //   61: iadd
/*     */       //   62: iload_3
/*     */       //   63: iconst_1
/*     */       //   64: invokespecial dateCharacterOffset : (Ljava/lang/String;IIZ)I
/*     */       //   67: istore #12
/*     */       //   69: aload #11
/*     */       //   71: iload #4
/*     */       //   73: iload #12
/*     */       //   75: invokevirtual region : (II)Ljava/util/regex/Matcher;
/*     */       //   78: pop
/*     */       //   79: nop
/*     */       //   80: iload #5
/*     */       //   82: iconst_m1
/*     */       //   83: if_icmpne -> 175
/*     */       //   86: aload #11
/*     */       //   88: invokestatic access$getTIME_PATTERN$cp : ()Ljava/util/regex/Pattern;
/*     */       //   91: invokevirtual usePattern : (Ljava/util/regex/Pattern;)Ljava/util/regex/Matcher;
/*     */       //   94: invokevirtual matches : ()Z
/*     */       //   97: ifeq -> 175
/*     */       //   100: aload #11
/*     */       //   102: iconst_1
/*     */       //   103: invokevirtual group : (I)Ljava/lang/String;
/*     */       //   106: dup
/*     */       //   107: ldc 'matcher.group(1)'
/*     */       //   109: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   112: astore #13
/*     */       //   114: iconst_0
/*     */       //   115: istore #14
/*     */       //   117: aload #13
/*     */       //   119: invokestatic parseInt : (Ljava/lang/String;)I
/*     */       //   122: istore #5
/*     */       //   124: aload #11
/*     */       //   126: iconst_2
/*     */       //   127: invokevirtual group : (I)Ljava/lang/String;
/*     */       //   130: dup
/*     */       //   131: ldc 'matcher.group(2)'
/*     */       //   133: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   136: astore #13
/*     */       //   138: iconst_0
/*     */       //   139: istore #14
/*     */       //   141: aload #13
/*     */       //   143: invokestatic parseInt : (Ljava/lang/String;)I
/*     */       //   146: istore #6
/*     */       //   148: aload #11
/*     */       //   150: iconst_3
/*     */       //   151: invokevirtual group : (I)Ljava/lang/String;
/*     */       //   154: dup
/*     */       //   155: ldc 'matcher.group(3)'
/*     */       //   157: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   160: astore #13
/*     */       //   162: iconst_0
/*     */       //   163: istore #14
/*     */       //   165: aload #13
/*     */       //   167: invokestatic parseInt : (Ljava/lang/String;)I
/*     */       //   170: istore #7
/*     */       //   172: goto -> 381
/*     */       //   175: iload #8
/*     */       //   177: iconst_m1
/*     */       //   178: if_icmpne -> 222
/*     */       //   181: aload #11
/*     */       //   183: invokestatic access$getDAY_OF_MONTH_PATTERN$cp : ()Ljava/util/regex/Pattern;
/*     */       //   186: invokevirtual usePattern : (Ljava/util/regex/Pattern;)Ljava/util/regex/Matcher;
/*     */       //   189: invokevirtual matches : ()Z
/*     */       //   192: ifeq -> 222
/*     */       //   195: aload #11
/*     */       //   197: iconst_1
/*     */       //   198: invokevirtual group : (I)Ljava/lang/String;
/*     */       //   201: dup
/*     */       //   202: ldc 'matcher.group(1)'
/*     */       //   204: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   207: astore #13
/*     */       //   209: iconst_0
/*     */       //   210: istore #14
/*     */       //   212: aload #13
/*     */       //   214: invokestatic parseInt : (Ljava/lang/String;)I
/*     */       //   217: istore #8
/*     */       //   219: goto -> 381
/*     */       //   222: iload #9
/*     */       //   224: iconst_m1
/*     */       //   225: if_icmpne -> 334
/*     */       //   228: aload #11
/*     */       //   230: invokestatic access$getMONTH_PATTERN$cp : ()Ljava/util/regex/Pattern;
/*     */       //   233: invokevirtual usePattern : (Ljava/util/regex/Pattern;)Ljava/util/regex/Matcher;
/*     */       //   236: invokevirtual matches : ()Z
/*     */       //   239: ifeq -> 334
/*     */       //   242: aload #11
/*     */       //   244: iconst_1
/*     */       //   245: invokevirtual group : (I)Ljava/lang/String;
/*     */       //   248: dup
/*     */       //   249: ldc 'matcher.group(1)'
/*     */       //   251: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   254: astore #14
/*     */       //   256: getstatic java/util/Locale.US : Ljava/util/Locale;
/*     */       //   259: dup
/*     */       //   260: ldc_w 'Locale.US'
/*     */       //   263: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   266: astore #15
/*     */       //   268: iconst_0
/*     */       //   269: istore #16
/*     */       //   271: aload #14
/*     */       //   273: dup
/*     */       //   274: ifnonnull -> 287
/*     */       //   277: new java/lang/NullPointerException
/*     */       //   280: dup
/*     */       //   281: ldc 'null cannot be cast to non-null type java.lang.String'
/*     */       //   283: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   286: athrow
/*     */       //   287: aload #15
/*     */       //   289: invokevirtual toLowerCase : (Ljava/util/Locale;)Ljava/lang/String;
/*     */       //   292: dup
/*     */       //   293: ldc_w '(this as java.lang.String).toLowerCase(locale)'
/*     */       //   296: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   299: astore #13
/*     */       //   301: invokestatic access$getMONTH_PATTERN$cp : ()Ljava/util/regex/Pattern;
/*     */       //   304: invokevirtual pattern : ()Ljava/lang/String;
/*     */       //   307: dup
/*     */       //   308: ldc_w 'MONTH_PATTERN.pattern()'
/*     */       //   311: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   314: checkcast java/lang/CharSequence
/*     */       //   317: aload #13
/*     */       //   319: iconst_0
/*     */       //   320: iconst_0
/*     */       //   321: bipush #6
/*     */       //   323: aconst_null
/*     */       //   324: invokestatic indexOf$default : (Ljava/lang/CharSequence;Ljava/lang/String;IZILjava/lang/Object;)I
/*     */       //   327: iconst_4
/*     */       //   328: idiv
/*     */       //   329: istore #9
/*     */       //   331: goto -> 381
/*     */       //   334: iload #10
/*     */       //   336: iconst_m1
/*     */       //   337: if_icmpne -> 381
/*     */       //   340: aload #11
/*     */       //   342: invokestatic access$getYEAR_PATTERN$cp : ()Ljava/util/regex/Pattern;
/*     */       //   345: invokevirtual usePattern : (Ljava/util/regex/Pattern;)Ljava/util/regex/Matcher;
/*     */       //   348: invokevirtual matches : ()Z
/*     */       //   351: ifeq -> 381
/*     */       //   354: aload #11
/*     */       //   356: iconst_1
/*     */       //   357: invokevirtual group : (I)Ljava/lang/String;
/*     */       //   360: dup
/*     */       //   361: ldc 'matcher.group(1)'
/*     */       //   363: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   366: astore #13
/*     */       //   368: iconst_0
/*     */       //   369: istore #14
/*     */       //   371: aload #13
/*     */       //   373: invokestatic parseInt : (Ljava/lang/String;)I
/*     */       //   376: istore #10
/*     */       //   378: goto -> 381
/*     */       //   381: aload_0
/*     */       //   382: checkcast okhttp3/Cookie$Companion
/*     */       //   385: aload_1
/*     */       //   386: iload #12
/*     */       //   388: iconst_1
/*     */       //   389: iadd
/*     */       //   390: iload_3
/*     */       //   391: iconst_0
/*     */       //   392: invokespecial dateCharacterOffset : (Ljava/lang/String;IIZ)I
/*     */       //   395: istore #4
/*     */       //   397: goto -> 47
/*     */       //   400: bipush #99
/*     */       //   402: bipush #70
/*     */       //   404: iload #10
/*     */       //   406: istore #12
/*     */       //   408: iload #12
/*     */       //   410: if_icmple -> 417
/*     */       //   413: pop
/*     */       //   414: goto -> 430
/*     */       //   417: iload #12
/*     */       //   419: if_icmplt -> 430
/*     */       //   422: iload #10
/*     */       //   424: sipush #1900
/*     */       //   427: iadd
/*     */       //   428: istore #10
/*     */       //   430: bipush #69
/*     */       //   432: iconst_0
/*     */       //   433: iload #10
/*     */       //   435: istore #12
/*     */       //   437: iload #12
/*     */       //   439: if_icmple -> 446
/*     */       //   442: pop
/*     */       //   443: goto -> 459
/*     */       //   446: iload #12
/*     */       //   448: if_icmplt -> 459
/*     */       //   451: iload #10
/*     */       //   453: sipush #2000
/*     */       //   456: iadd
/*     */       //   457: istore #10
/*     */       //   459: iload #10
/*     */       //   461: sipush #1601
/*     */       //   464: if_icmplt -> 471
/*     */       //   467: iconst_1
/*     */       //   468: goto -> 472
/*     */       //   471: iconst_0
/*     */       //   472: istore #12
/*     */       //   474: iconst_0
/*     */       //   475: istore #13
/*     */       //   477: iconst_0
/*     */       //   478: istore #14
/*     */       //   480: iconst_0
/*     */       //   481: istore #14
/*     */       //   483: iconst_0
/*     */       //   484: istore #15
/*     */       //   486: iload #12
/*     */       //   488: ifne -> 515
/*     */       //   491: iconst_0
/*     */       //   492: istore #16
/*     */       //   494: ldc_w 'Failed requirement.'
/*     */       //   497: astore #15
/*     */       //   499: new java/lang/IllegalArgumentException
/*     */       //   502: dup
/*     */       //   503: aload #15
/*     */       //   505: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   508: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   511: checkcast java/lang/Throwable
/*     */       //   514: athrow
/*     */       //   515: iload #9
/*     */       //   517: iconst_m1
/*     */       //   518: if_icmpeq -> 525
/*     */       //   521: iconst_1
/*     */       //   522: goto -> 526
/*     */       //   525: iconst_0
/*     */       //   526: istore #12
/*     */       //   528: iconst_0
/*     */       //   529: istore #13
/*     */       //   531: iconst_0
/*     */       //   532: istore #14
/*     */       //   534: iconst_0
/*     */       //   535: istore #14
/*     */       //   537: iconst_0
/*     */       //   538: istore #15
/*     */       //   540: iload #12
/*     */       //   542: ifne -> 569
/*     */       //   545: iconst_0
/*     */       //   546: istore #16
/*     */       //   548: ldc_w 'Failed requirement.'
/*     */       //   551: astore #15
/*     */       //   553: new java/lang/IllegalArgumentException
/*     */       //   556: dup
/*     */       //   557: aload #15
/*     */       //   559: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   562: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   565: checkcast java/lang/Throwable
/*     */       //   568: athrow
/*     */       //   569: bipush #31
/*     */       //   571: iconst_1
/*     */       //   572: iload #8
/*     */       //   574: istore #12
/*     */       //   576: iload #12
/*     */       //   578: if_icmple -> 585
/*     */       //   581: pop
/*     */       //   582: goto -> 594
/*     */       //   585: iload #12
/*     */       //   587: if_icmplt -> 594
/*     */       //   590: iconst_1
/*     */       //   591: goto -> 595
/*     */       //   594: iconst_0
/*     */       //   595: istore #12
/*     */       //   597: iconst_0
/*     */       //   598: istore #13
/*     */       //   600: iconst_0
/*     */       //   601: istore #14
/*     */       //   603: iconst_0
/*     */       //   604: istore #14
/*     */       //   606: iconst_0
/*     */       //   607: istore #15
/*     */       //   609: iload #12
/*     */       //   611: ifne -> 638
/*     */       //   614: iconst_0
/*     */       //   615: istore #16
/*     */       //   617: ldc_w 'Failed requirement.'
/*     */       //   620: astore #15
/*     */       //   622: new java/lang/IllegalArgumentException
/*     */       //   625: dup
/*     */       //   626: aload #15
/*     */       //   628: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   631: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   634: checkcast java/lang/Throwable
/*     */       //   637: athrow
/*     */       //   638: bipush #23
/*     */       //   640: iconst_0
/*     */       //   641: iload #5
/*     */       //   643: istore #12
/*     */       //   645: iload #12
/*     */       //   647: if_icmple -> 654
/*     */       //   650: pop
/*     */       //   651: goto -> 663
/*     */       //   654: iload #12
/*     */       //   656: if_icmplt -> 663
/*     */       //   659: iconst_1
/*     */       //   660: goto -> 664
/*     */       //   663: iconst_0
/*     */       //   664: istore #12
/*     */       //   666: iconst_0
/*     */       //   667: istore #13
/*     */       //   669: iconst_0
/*     */       //   670: istore #14
/*     */       //   672: iconst_0
/*     */       //   673: istore #14
/*     */       //   675: iconst_0
/*     */       //   676: istore #15
/*     */       //   678: iload #12
/*     */       //   680: ifne -> 707
/*     */       //   683: iconst_0
/*     */       //   684: istore #16
/*     */       //   686: ldc_w 'Failed requirement.'
/*     */       //   689: astore #15
/*     */       //   691: new java/lang/IllegalArgumentException
/*     */       //   694: dup
/*     */       //   695: aload #15
/*     */       //   697: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   700: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   703: checkcast java/lang/Throwable
/*     */       //   706: athrow
/*     */       //   707: bipush #59
/*     */       //   709: iconst_0
/*     */       //   710: iload #6
/*     */       //   712: istore #12
/*     */       //   714: iload #12
/*     */       //   716: if_icmple -> 723
/*     */       //   719: pop
/*     */       //   720: goto -> 732
/*     */       //   723: iload #12
/*     */       //   725: if_icmplt -> 732
/*     */       //   728: iconst_1
/*     */       //   729: goto -> 733
/*     */       //   732: iconst_0
/*     */       //   733: istore #12
/*     */       //   735: iconst_0
/*     */       //   736: istore #13
/*     */       //   738: iconst_0
/*     */       //   739: istore #14
/*     */       //   741: iconst_0
/*     */       //   742: istore #14
/*     */       //   744: iconst_0
/*     */       //   745: istore #15
/*     */       //   747: iload #12
/*     */       //   749: ifne -> 776
/*     */       //   752: iconst_0
/*     */       //   753: istore #16
/*     */       //   755: ldc_w 'Failed requirement.'
/*     */       //   758: astore #15
/*     */       //   760: new java/lang/IllegalArgumentException
/*     */       //   763: dup
/*     */       //   764: aload #15
/*     */       //   766: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   769: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   772: checkcast java/lang/Throwable
/*     */       //   775: athrow
/*     */       //   776: bipush #59
/*     */       //   778: iconst_0
/*     */       //   779: iload #7
/*     */       //   781: istore #12
/*     */       //   783: iload #12
/*     */       //   785: if_icmple -> 792
/*     */       //   788: pop
/*     */       //   789: goto -> 801
/*     */       //   792: iload #12
/*     */       //   794: if_icmplt -> 801
/*     */       //   797: iconst_1
/*     */       //   798: goto -> 802
/*     */       //   801: iconst_0
/*     */       //   802: istore #12
/*     */       //   804: iconst_0
/*     */       //   805: istore #13
/*     */       //   807: iconst_0
/*     */       //   808: istore #14
/*     */       //   810: iconst_0
/*     */       //   811: istore #14
/*     */       //   813: iconst_0
/*     */       //   814: istore #15
/*     */       //   816: iload #12
/*     */       //   818: ifne -> 845
/*     */       //   821: iconst_0
/*     */       //   822: istore #16
/*     */       //   824: ldc_w 'Failed requirement.'
/*     */       //   827: astore #15
/*     */       //   829: new java/lang/IllegalArgumentException
/*     */       //   832: dup
/*     */       //   833: aload #15
/*     */       //   835: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   838: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   841: checkcast java/lang/Throwable
/*     */       //   844: athrow
/*     */       //   845: new java/util/GregorianCalendar
/*     */       //   848: dup
/*     */       //   849: getstatic okhttp3/internal/Util.UTC : Ljava/util/TimeZone;
/*     */       //   852: invokespecial <init> : (Ljava/util/TimeZone;)V
/*     */       //   855: astore #12
/*     */       //   857: iconst_0
/*     */       //   858: istore #13
/*     */       //   860: iconst_0
/*     */       //   861: istore #14
/*     */       //   863: aload #12
/*     */       //   865: astore #15
/*     */       //   867: iconst_0
/*     */       //   868: istore #16
/*     */       //   870: aload #15
/*     */       //   872: iconst_0
/*     */       //   873: invokevirtual setLenient : (Z)V
/*     */       //   876: aload #15
/*     */       //   878: iconst_1
/*     */       //   879: iload #10
/*     */       //   881: invokevirtual set : (II)V
/*     */       //   884: aload #15
/*     */       //   886: iconst_2
/*     */       //   887: iload #9
/*     */       //   889: iconst_1
/*     */       //   890: isub
/*     */       //   891: invokevirtual set : (II)V
/*     */       //   894: aload #15
/*     */       //   896: iconst_5
/*     */       //   897: iload #8
/*     */       //   899: invokevirtual set : (II)V
/*     */       //   902: aload #15
/*     */       //   904: bipush #11
/*     */       //   906: iload #5
/*     */       //   908: invokevirtual set : (II)V
/*     */       //   911: aload #15
/*     */       //   913: bipush #12
/*     */       //   915: iload #6
/*     */       //   917: invokevirtual set : (II)V
/*     */       //   920: aload #15
/*     */       //   922: bipush #13
/*     */       //   924: iload #7
/*     */       //   926: invokevirtual set : (II)V
/*     */       //   929: aload #15
/*     */       //   931: bipush #14
/*     */       //   933: iconst_0
/*     */       //   934: invokevirtual set : (II)V
/*     */       //   937: aload #15
/*     */       //   939: invokevirtual getTimeInMillis : ()J
/*     */       //   942: lreturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #487	-> 0
/*     */       //   #488	-> 3
/*     */       //   #490	-> 17
/*     */       //   #491	-> 20
/*     */       //   #492	-> 23
/*     */       //   #493	-> 26
/*     */       //   #494	-> 29
/*     */       //   #495	-> 32
/*     */       //   #496	-> 35
/*     */       //   #498	-> 47
/*     */       //   #499	-> 53
/*     */       //   #500	-> 69
/*     */       //   #502	-> 79
/*     */       //   #503	-> 80
/*     */       //   #504	-> 100
/*     */       //   #505	-> 124
/*     */       //   #506	-> 148
/*     */       //   #508	-> 175
/*     */       //   #509	-> 195
/*     */       //   #511	-> 222
/*     */       //   #512	-> 242
/*     */       //   #512	-> 299
/*     */       //   #513	-> 301
/*     */       //   #515	-> 334
/*     */       //   #516	-> 354
/*     */       //   #518	-> 381
/*     */       //   #520	-> 381
/*     */       //   #498	-> 397
/*     */       //   #524	-> 400
/*     */       //   #525	-> 430
/*     */       //   #529	-> 459
/*     */       //   #530	-> 515
/*     */       //   #531	-> 569
/*     */       //   #532	-> 638
/*     */       //   #533	-> 707
/*     */       //   #534	-> 776
/*     */       //   #536	-> 845
/*     */       //   #537	-> 870
/*     */       //   #538	-> 876
/*     */       //   #539	-> 884
/*     */       //   #540	-> 894
/*     */       //   #541	-> 902
/*     */       //   #542	-> 911
/*     */       //   #543	-> 920
/*     */       //   #544	-> 929
/*     */       //   #545	-> 937
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   301	30	13	monthString	Ljava/lang/String;
/*     */       //   69	328	12	end	I
/*     */       //   867	76	15	$this$apply	Ljava/util/GregorianCalendar;
/*     */       //   870	73	16	$i$a$-apply-Cookie$Companion$parseExpires$1	I
/*     */       //   47	896	11	matcher	Ljava/util/regex/Matcher;
/*     */       //   35	908	10	year	I
/*     */       //   32	911	9	month	I
/*     */       //   29	914	8	dayOfMonth	I
/*     */       //   26	917	7	second	I
/*     */       //   23	920	6	minute	I
/*     */       //   20	923	5	hour	I
/*     */       //   3	940	4	pos	I
/*     */       //   0	943	0	this	Lokhttp3/Cookie$Companion;
/*     */       //   0	943	1	s	Ljava/lang/String;
/*     */       //   0	943	2	pos	I
/*     */       //   0	943	3	limit	I
/*     */     }
/*     */     
/*     */     private final int dateCharacterOffset(String input, int pos, int limit, boolean invert) {
/*     */       // Byte code:
/*     */       //   0: iload_2
/*     */       //   1: istore #5
/*     */       //   3: iload_3
/*     */       //   4: istore #6
/*     */       //   6: iload #5
/*     */       //   8: iload #6
/*     */       //   10: if_icmpge -> 146
/*     */       //   13: aload_1
/*     */       //   14: iload #5
/*     */       //   16: invokevirtual charAt : (I)C
/*     */       //   19: istore #7
/*     */       //   21: iload #7
/*     */       //   23: bipush #32
/*     */       //   25: if_icmpge -> 35
/*     */       //   28: iload #7
/*     */       //   30: bipush #9
/*     */       //   32: if_icmpne -> 115
/*     */       //   35: iload #7
/*     */       //   37: bipush #127
/*     */       //   39: if_icmpge -> 115
/*     */       //   42: bipush #57
/*     */       //   44: bipush #48
/*     */       //   46: iload #7
/*     */       //   48: istore #9
/*     */       //   50: iload #9
/*     */       //   52: if_icmpgt -> 63
/*     */       //   55: iload #9
/*     */       //   57: if_icmpge -> 115
/*     */       //   60: goto -> 64
/*     */       //   63: pop
/*     */       //   64: bipush #122
/*     */       //   66: bipush #97
/*     */       //   68: iload #7
/*     */       //   70: istore #9
/*     */       //   72: iload #9
/*     */       //   74: if_icmpgt -> 85
/*     */       //   77: iload #9
/*     */       //   79: if_icmpge -> 115
/*     */       //   82: goto -> 86
/*     */       //   85: pop
/*     */       //   86: bipush #90
/*     */       //   88: bipush #65
/*     */       //   90: iload #7
/*     */       //   92: istore #9
/*     */       //   94: iload #9
/*     */       //   96: if_icmpgt -> 107
/*     */       //   99: iload #9
/*     */       //   101: if_icmpge -> 115
/*     */       //   104: goto -> 108
/*     */       //   107: pop
/*     */       //   108: iload #7
/*     */       //   110: bipush #58
/*     */       //   112: if_icmpne -> 119
/*     */       //   115: iconst_1
/*     */       //   116: goto -> 120
/*     */       //   119: iconst_0
/*     */       //   120: istore #8
/*     */       //   122: iload #8
/*     */       //   124: iload #4
/*     */       //   126: ifne -> 133
/*     */       //   129: iconst_1
/*     */       //   130: goto -> 134
/*     */       //   133: iconst_0
/*     */       //   134: if_icmpne -> 140
/*     */       //   137: iload #5
/*     */       //   139: ireturn
/*     */       //   140: iinc #5, 1
/*     */       //   143: goto -> 6
/*     */       //   146: iload_3
/*     */       //   147: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #554	-> 0
/*     */       //   #554	-> 6
/*     */       //   #555	-> 13
/*     */       //   #556	-> 21
/*     */       //   #557	-> 21
/*     */       //   #558	-> 21
/*     */       //   #559	-> 21
/*     */       //   #560	-> 21
/*     */       //   #556	-> 23
/*     */       //   #557	-> 42
/*     */       //   #558	-> 64
/*     */       //   #559	-> 86
/*     */       //   #560	-> 110
/*     */       //   #556	-> 120
/*     */       //   #561	-> 122
/*     */       //   #554	-> 140
/*     */       //   #563	-> 146
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   122	18	8	dateCharacter	Z
/*     */       //   21	119	7	c	I
/*     */       //   13	130	5	i	I
/*     */       //   0	148	0	this	Lokhttp3/Cookie$Companion;
/*     */       //   0	148	1	input	Ljava/lang/String;
/*     */       //   0	148	2	pos	I
/*     */       //   0	148	3	limit	I
/*     */       //   0	148	4	invert	Z
/*     */     }
/*     */     
/*     */     private final long parseMaxAge(String s) {
/*     */       try {
/*     */         String str = s;
/*     */         boolean bool = false;
/*     */         long parsed = Long.parseLong(str);
/*     */         return (parsed <= 0L) ? Long.MIN_VALUE : parsed;
/*     */       } catch (NumberFormatException e) {
/*     */         CharSequence charSequence = s;
/*     */         String str = "-?\\d+";
/*     */         boolean bool = false;
/*     */         Regex regex = new Regex(str);
/*     */         bool = false;
/*     */         if (regex.matches(charSequence))
/*     */           return StringsKt.startsWith$default(s, "-", false, 2, null) ? Long.MIN_VALUE : Long.MAX_VALUE; 
/*     */         throw (Throwable)e;
/*     */       } 
/*     */     }
/*     */     
/*     */     private final String parseDomain(String s) {
/*     */       boolean bool1 = !StringsKt.endsWith$default(s, ".", false, 2, null) ? true : false, bool2 = false, bool3 = false;
/*     */       bool3 = false;
/*     */       boolean bool4 = false;
/*     */       if (!bool1) {
/*     */         boolean bool = false;
/*     */         String str = "Failed requirement.";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       if (HostnamesKt.toCanonicalHost(StringsKt.removePrefix(s, ".")) != null)
/*     */         return HostnamesKt.toCanonicalHost(StringsKt.removePrefix(s, ".")); 
/*     */       HostnamesKt.toCanonicalHost(StringsKt.removePrefix(s, "."));
/*     */       throw (Throwable)new IllegalArgumentException();
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @NotNull
/*     */     public final List<Cookie> parseAll(@NotNull HttpUrl url, @NotNull Headers headers) {
/*     */       Intrinsics.checkNotNullParameter(url, "url");
/*     */       Intrinsics.checkNotNullParameter(headers, "headers");
/*     */       List<String> cookieStrings = headers.values("Set-Cookie");
/*     */       List<Cookie> cookies = (List)null;
/*     */       byte b;
/*     */       int i;
/*     */       for (b = 0, i = cookieStrings.size(); b < i; b++) {
/*     */         if (parse(url, cookieStrings.get(b)) != null) {
/*     */           Cookie cookie = parse(url, cookieStrings.get(b));
/*     */           if (cookies == null) {
/*     */             boolean bool = false;
/*     */             cookies = new ArrayList();
/*     */           } 
/*     */           cookies.add(cookie);
/*     */         } else {
/*     */           parse(url, cookieStrings.get(b));
/*     */         } 
/*     */       } 
/*     */       Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(cookies), "Collections.unmodifiableList(cookies)");
/*     */       return (cookies != null) ? Collections.unmodifiableList(cookies) : CollectionsKt.emptyList();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Cookie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */