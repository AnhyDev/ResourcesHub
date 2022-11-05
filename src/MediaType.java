/*     */ package okhttp3;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\003\n\002\020\021\n\002\b\004\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\007\030\000 \0302\0020\001:\001\030B-\b\002\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\006\020\005\032\0020\003\022\f\020\006\032\b\022\004\022\0020\0030\007¢\006\002\020\bJ\026\020\013\032\004\030\0010\f2\n\b\002\020\r\032\004\030\0010\fH\007J\023\020\016\032\0020\0172\b\020\020\032\004\030\0010\001H\002J\b\020\021\032\0020\022H\026J\020\020\023\032\004\030\0010\0032\006\020\024\032\0020\003J\r\020\005\032\0020\003H\007¢\006\002\b\025J\b\020\026\032\0020\003H\026J\r\020\004\032\0020\003H\007¢\006\002\b\027R\016\020\002\032\0020\003X\004¢\006\002\n\000R\026\020\006\032\b\022\004\022\0020\0030\007X\004¢\006\004\n\002\020\tR\023\020\005\032\0020\0038\007¢\006\b\n\000\032\004\b\005\020\nR\023\020\004\032\0020\0038\007¢\006\b\n\000\032\004\b\004\020\n¨\006\031"}, d2 = {"Lokhttp3/MediaType;", "", "mediaType", "", "type", "subtype", "parameterNamesAndValues", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "[Ljava/lang/String;", "()Ljava/lang/String;", "charset", "Ljava/nio/charset/Charset;", "defaultValue", "equals", "", "other", "hashCode", "", "parameter", "name", "-deprecated_subtype", "toString", "-deprecated_type", "Companion", "okhttp"})
/*     */ public final class MediaType {
/*     */   private final String mediaType;
/*     */   @NotNull
/*     */   private final String type;
/*     */   @NotNull
/*     */   private final String subtype;
/*     */   private final String[] parameterNamesAndValues;
/*     */   private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
/*     */   private static final String QUOTED = "\"([^\"]*)\"";
/*     */   private static final Pattern TYPE_SUBTYPE;
/*     */   private static final Pattern PARAMETER;
/*     */   
/*     */   private MediaType(String mediaType, String type, String subtype, String[] parameterNamesAndValues) {
/*  28 */     this.mediaType = mediaType; this.type = type; this.subtype = subtype; this.parameterNamesAndValues = parameterNamesAndValues;
/*     */   }
/*     */   
/*     */   @JvmName(name = "type")
/*     */   @NotNull
/*     */   public final String type() {
/*  34 */     return this.type;
/*     */   }
/*     */   @JvmName(name = "subtype")
/*     */   @NotNull
/*     */   public final String subtype() {
/*  39 */     return this.subtype;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JvmOverloads
/*     */   @Nullable
/*     */   public final Charset charset(@Nullable Charset defaultValue) {
/*  51 */     if (parameter("charset") != null) { Charset charset1; String charset = parameter("charset");
/*     */       try {
/*  53 */         charset1 = Charset.forName(charset);
/*  54 */       } catch (IllegalArgumentException _) {
/*  55 */         charset1 = defaultValue;
/*     */       } 
/*     */       return charset1; }
/*     */     
/*     */     parameter("charset");
/*     */     return defaultValue;
/*     */   }
/*     */   @Nullable
/*     */   public final String parameter(@NotNull String name) {
/*  64 */     Intrinsics.checkNotNullParameter(name, "name"); int i = RangesKt.step((IntProgression)ArraysKt.getIndices((Object[])this.parameterNamesAndValues), 2).getFirst(), j = RangesKt.step((IntProgression)ArraysKt.getIndices((Object[])this.parameterNamesAndValues), 2).getLast(), k = RangesKt.step((IntProgression)ArraysKt.getIndices((Object[])this.parameterNamesAndValues), 2).getStep(); if ((k >= 0) ? (i <= j) : (i >= j))
/*  65 */       while (true) { if (StringsKt.equals(this.parameterNamesAndValues[i], name, true))
/*  66 */           return this.parameterNamesAndValues[i + 1];  if (i != j) {
/*     */           int m = i + k; continue;
/*     */         }  break; }
/*  69 */         return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "type"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_type")
/*     */   @NotNull
/*     */   public final String -deprecated_type() {
/*  77 */     return this.type;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "subtype"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_subtype")
/*     */   @NotNull
/*     */   public final String -deprecated_subtype() {
/*  84 */     return this.subtype;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String toString() {
/*  90 */     return this.mediaType;
/*     */   } public boolean equals(@Nullable Object other) {
/*  92 */     return (other instanceof MediaType && Intrinsics.areEqual(((MediaType)other).mediaType, this.mediaType));
/*     */   } public int hashCode() {
/*  94 */     return this.mediaType.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public static final Companion Companion = new Companion(null); static { TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
/* 100 */     PARAMETER = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?"); }
/*     */   @JvmOverloads @Nullable public final Charset charset() { return charset$default(this, null, 1, null); } @JvmStatic @JvmName(name = "get") @NotNull public static final MediaType get(@NotNull String $this$toMediaType) { return Companion.get($this$toMediaType); } @JvmStatic
/*     */   @JvmName(name = "parse")
/*     */   @Nullable
/*     */   public static final MediaType parse(@NotNull String $this$toMediaTypeOrNull) { return Companion.parse($this$toMediaTypeOrNull); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000$\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\003\n\002\030\002\n\002\b\007\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\025\020\n\032\0020\0132\006\020\f\032\0020\007H\007¢\006\002\b\rJ\027\020\016\032\004\030\0010\0132\006\020\f\032\0020\007H\007¢\006\002\b\017J\021\020\020\032\0020\013*\0020\007H\007¢\006\002\b\nJ\023\020\021\032\004\030\0010\013*\0020\007H\007¢\006\002\b\016R\026\020\003\032\n \005*\004\030\0010\0040\004X\004¢\006\002\n\000R\016\020\006\032\0020\007XT¢\006\002\n\000R\016\020\b\032\0020\007XT¢\006\002\n\000R\026\020\t\032\n \005*\004\030\0010\0040\004X\004¢\006\002\n\000¨\006\022"}, d2 = {"Lokhttp3/MediaType$Companion;", "", "()V", "PARAMETER", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "QUOTED", "", "TOKEN", "TYPE_SUBTYPE", "get", "Lokhttp3/MediaType;", "mediaType", "-deprecated_get", "parse", "-deprecated_parse", "toMediaType", "toMediaTypeOrNull", "okhttp"})
/*     */   public static final class Companion
/*     */   {
/*     */     private Companion() {} @JvmStatic
/*     */     @JvmName(name = "get")
/*     */     @NotNull
/* 110 */     public final MediaType get(@NotNull String $this$toMediaType) { Intrinsics.checkNotNullParameter($this$toMediaType, "$this$toMediaType"); Matcher typeSubtype = MediaType.TYPE_SUBTYPE.matcher($this$toMediaType);
/* 111 */       boolean bool = typeSubtype.lookingAt(); boolean bool1 = false, bool2 = false; if (!bool)
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
/* 182 */         int $i$a$-require-MediaType$Companion$toMediaType$1 = 0; String str = "No subtype found for: \"" + $this$toMediaType + '"'; throw (Throwable)new IllegalArgumentException(str.toString()); }  Intrinsics.checkNotNullExpressionValue(typeSubtype.group(1), "typeSubtype.group(1)"); String str1 = typeSubtype.group(1); Intrinsics.checkNotNullExpressionValue(Locale.US, "Locale.US"); Locale locale1 = Locale.US; boolean bool4 = false; if (str1 == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str1.toLowerCase(locale1), "(this as java.lang.String).toLowerCase(locale)"); String type = str1.toLowerCase(locale1); Intrinsics.checkNotNullExpressionValue(typeSubtype.group(2), "typeSubtype.group(2)"); String str2 = typeSubtype.group(2); Intrinsics.checkNotNullExpressionValue(Locale.US, "Locale.US"); Locale locale2 = Locale.US; boolean bool5 = false; if (str2 == null)
/* 183 */         throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str2.toLowerCase(locale2), "(this as java.lang.String).toLowerCase(locale)"); String subtype = str2.toLowerCase(locale2); boolean bool3 = false; List<String> parameterNamesAndValues = new ArrayList(); Matcher parameter = MediaType.PARAMETER.matcher($this$toMediaType); int s = typeSubtype.end(); while (s < $this$toMediaType.length()) { parameter.region(s, $this$toMediaType.length()); boolean bool6 = parameter.lookingAt(); boolean bool7 = false, bool8 = false; if (!bool6) { int $i$a$-require-MediaType$Companion$toMediaType$2 = 0; String str5 = $this$toMediaType; int j = s; boolean bool11 = false; Intrinsics.checkNotNullExpressionValue(str5.substring(j), "(this as java.lang.String).substring(startIndex)"); String str4 = "Parameter is not formatted correctly: \"" + str5.substring(j) + "\" for: \"" + $this$toMediaType + '"'; throw (Throwable)new IllegalArgumentException(str4.toString()); }  String name = parameter.group(1); if (name == null) { s = parameter.end(); continue; }  String token = parameter.group(2); String str3 = token; boolean bool9 = true; int i = token.length() - 1; boolean bool10 = false; Intrinsics.checkNotNullExpressionValue(str3.substring(bool9, i), "(this as java.lang.Strin…ing(startIndex, endIndex)"); String value = (token == null) ? parameter.group(3) : ((StringsKt.startsWith$default(token, "'", false, 2, null) && StringsKt.endsWith$default(token, "'", false, 2, null) && token.length() > 2) ? str3.substring(bool9, i) : token); List<String> list = parameterNamesAndValues; bool9 = false; list.add(name); list = parameterNamesAndValues; bool9 = false; list.add(value); s = parameter.end(); }  Collection<String> $this$toTypedArray$iv = parameterNamesAndValues; int $i$f$toTypedArray = 0; Collection<String> thisCollection$iv = $this$toTypedArray$iv;
/* 184 */       if (thisCollection$iv.toArray(new String[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  return new MediaType($this$toMediaType, type, subtype, thisCollection$iv.toArray(new String[0]), null); }
/*     */ 
/*     */     
/*     */     @JvmStatic
/*     */     @JvmName(name = "parse")
/*     */     @Nullable
/*     */     public final MediaType parse(@NotNull String $this$toMediaTypeOrNull) {
/*     */       MediaType mediaType;
/*     */       Intrinsics.checkNotNullParameter($this$toMediaTypeOrNull, "$this$toMediaTypeOrNull");
/*     */       try {
/*     */         mediaType = get($this$toMediaTypeOrNull);
/*     */       } catch (IllegalArgumentException _) {
/*     */         mediaType = null;
/*     */       } 
/*     */       return mediaType;
/*     */     }
/*     */     
/*     */     @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.MediaType.Companion.toMediaType"}, expression = "mediaType.toMediaType()"), level = DeprecationLevel.ERROR)
/*     */     @JvmName(name = "-deprecated_get")
/*     */     @NotNull
/*     */     public final MediaType -deprecated_get(@NotNull String mediaType) {
/*     */       Intrinsics.checkNotNullParameter(mediaType, "mediaType");
/*     */       return get(mediaType);
/*     */     }
/*     */     
/*     */     @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.MediaType.Companion.toMediaTypeOrNull"}, expression = "mediaType.toMediaTypeOrNull()"), level = DeprecationLevel.ERROR)
/*     */     @JvmName(name = "-deprecated_parse")
/*     */     @Nullable
/*     */     public final MediaType -deprecated_parse(@NotNull String mediaType) {
/*     */       Intrinsics.checkNotNullParameter(mediaType, "mediaType");
/*     */       return parse(mediaType);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/MediaType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */