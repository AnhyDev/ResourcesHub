/*     */ package okhttp3;
/*     */ 
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.collections.MapsKt;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\003\n\002\020$\n\002\b\003\n\002\030\002\n\002\b\005\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\005\030\0002\0020\001B\027\b\026\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003¢\006\002\020\005B#\022\006\020\002\032\0020\003\022\024\020\006\032\020\022\006\022\004\030\0010\003\022\004\022\0020\0030\007¢\006\002\020\bJ\033\020\006\032\020\022\006\022\004\030\0010\003\022\004\022\0020\0030\007H\007¢\006\002\b\016J\r\020\n\032\0020\013H\007¢\006\002\b\017J\023\020\020\032\0020\0212\b\020\022\032\004\030\0010\001H\002J\b\020\023\032\0020\024H\026J\017\020\004\032\004\030\0010\003H\007¢\006\002\b\025J\r\020\002\032\0020\003H\007¢\006\002\b\026J\b\020\027\032\0020\003H\026J\016\020\030\032\0020\0002\006\020\n\032\0020\013R!\020\006\032\020\022\006\022\004\030\0010\003\022\004\022\0020\0030\0078G¢\006\b\n\000\032\004\b\006\020\tR\021\020\n\032\0020\0138G¢\006\006\032\004\b\n\020\fR\023\020\004\032\004\030\0010\0038G¢\006\006\032\004\b\004\020\rR\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020\r¨\006\031"}, d2 = {"Lokhttp3/Challenge;", "", "scheme", "", "realm", "(Ljava/lang/String;Ljava/lang/String;)V", "authParams", "", "(Ljava/lang/String;Ljava/util/Map;)V", "()Ljava/util/Map;", "charset", "Ljava/nio/charset/Charset;", "()Ljava/nio/charset/Charset;", "()Ljava/lang/String;", "-deprecated_authParams", "-deprecated_charset", "equals", "", "other", "hashCode", "", "-deprecated_realm", "-deprecated_scheme", "toString", "withCharset", "okhttp"})
/*     */ public final class Challenge
/*     */ {
/*     */   @NotNull
/*     */   private final Map<String, String> authParams;
/*     */   @NotNull
/*     */   private final String scheme;
/*     */   
/*     */   public Challenge(@NotNull String scheme, @NotNull Map authParams) {
/*  29 */     this.scheme = scheme;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     boolean bool1 = false; Map<Object, Object> newAuthParams = new LinkedHashMap<>(); Iterator<Map.Entry> iterator; Map map; boolean bool2;
/*  62 */     for (map = authParams, bool2 = false, iterator = map.entrySet().iterator(); iterator.hasNext(); ) { Map.Entry entry1 = iterator.next(), entry2 = entry1; boolean bool = false; String str1 = (String)entry2.getKey(); entry2 = entry1; bool = false; String value = (String)entry2.getValue();
/*  63 */       if (str1 != null) { String str = str1; Intrinsics.checkNotNullExpressionValue(Locale.US, "US"); Locale locale = Locale.US; boolean bool3 = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.toLowerCase(locale), "(this as java.lang.String).toLowerCase(locale)"); } else { str.toLowerCase(locale); }  String newKey = null;
/*  64 */       newAuthParams.put(newKey, value); }
/*     */     
/*  66 */     Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableMap(newAuthParams), "unmodifiableMap<String?, String>(newAuthParams)"); this.authParams = Collections.unmodifiableMap(newAuthParams); } @JvmName(name = "scheme") @NotNull public final String scheme() { return this.scheme; }
/*     */   @JvmName(name = "authParams")
/*     */   @NotNull
/*     */   public final Map<String, String> authParams() { return this.authParams; }
/*     */   @NotNull
/*  71 */   public final Challenge withCharset(@NotNull Charset charset) { Intrinsics.checkNotNullParameter(charset, "charset"); Map<String, String> authParams = MapsKt.toMutableMap(this.authParams);
/*  72 */     Intrinsics.checkNotNullExpressionValue(charset.name(), "charset.name()"); authParams.put("charset", charset.name());
/*  73 */     return new Challenge(this.scheme, authParams); }
/*     */   @JvmName(name = "realm")
/*     */   @Nullable
/*     */   public final String realm() {
/*     */     return this.authParams.get("realm");
/*     */   } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "scheme"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_scheme")
/*     */   @NotNull
/*  81 */   public final String -deprecated_scheme() { return this.scheme; }
/*     */   @JvmName(name = "charset") @NotNull public final Charset charset() { String charset = this.authParams.get("charset"); if (charset != null)
/*     */       try {
/*     */         Intrinsics.checkNotNullExpressionValue(Charset.forName(charset), "Charset.forName(charset)"); return Charset.forName(charset);
/*     */       } catch (Exception exception) {} 
/*     */     Intrinsics.checkNotNullExpressionValue(StandardCharsets.ISO_8859_1, "ISO_8859_1");
/*     */     return StandardCharsets.ISO_8859_1; }
/*  88 */   public Challenge(@NotNull String scheme, @NotNull String realm) { this(scheme, Collections.singletonMap("realm", realm)); } @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "authParams"), level = DeprecationLevel.ERROR) @JvmName(name = "-deprecated_authParams") @NotNull public final Map<String, String> -deprecated_authParams() { return this.authParams; }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "realm"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_realm")
/*     */   @Nullable
/*     */   public final String -deprecated_realm() {
/*  95 */     return realm();
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "charset"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_charset")
/*     */   @NotNull
/*     */   public final Charset -deprecated_charset() {
/* 102 */     return charset();
/*     */   }
/*     */   public boolean equals(@Nullable Object other) {
/* 105 */     return (other instanceof Challenge && Intrinsics.areEqual(((Challenge)other).scheme, this.scheme) && Intrinsics.areEqual(((Challenge)other).authParams, this.authParams));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 111 */     int result = 29;
/* 112 */     result = 31 * result + this.scheme.hashCode();
/* 113 */     result = 31 * result + this.authParams.hashCode();
/* 114 */     return result;
/*     */   } @NotNull
/*     */   public String toString() {
/* 117 */     return this.scheme + " authParams=" + this.authParams;
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Challenge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */