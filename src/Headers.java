/*     */ package okhttp3;
/*     */ 
/*     */ import java.time.Instant;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.Pair;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.TuplesKt;
/*     */ import kotlin.collections.ArraysKt;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.JvmStatic;
/*     */ import kotlin.jvm.internal.ArrayIteratorKt;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.jvm.internal.StringCompanionObject;
/*     */ import kotlin.jvm.internal.markers.KMappedMarker;
/*     */ import kotlin.ranges.IntProgression;
/*     */ import kotlin.ranges.RangesKt;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.http.DatesKt;
/*     */ import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000f\n\002\030\002\n\002\020\034\n\002\030\002\n\002\020\016\n\000\n\002\020\021\n\002\b\003\n\002\020\b\n\002\b\002\n\002\020\t\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020(\n\002\b\002\n\002\020\"\n\000\n\002\030\002\n\002\b\002\n\002\020$\n\002\020 \n\002\b\006\030\000 '2\024\022\020\022\016\022\004\022\0020\003\022\004\022\0020\0030\0020\001:\002&'B\025\b\002\022\f\020\004\032\b\022\004\022\0020\0030\005¢\006\002\020\006J\006\020\013\032\0020\fJ\023\020\r\032\0020\0162\b\020\017\032\004\030\0010\020H\002J\023\020\021\032\004\030\0010\0032\006\020\022\032\0020\003H\002J\020\020\023\032\004\030\0010\0242\006\020\022\032\0020\003J\022\020\025\032\004\030\0010\0262\006\020\022\032\0020\003H\007J\b\020\027\032\0020\tH\026J\033\020\030\032\024\022\020\022\016\022\004\022\0020\003\022\004\022\0020\0030\0020\031H\002J\016\020\022\032\0020\0032\006\020\032\032\0020\tJ\f\020\033\032\b\022\004\022\0020\0030\034J\006\020\035\032\0020\036J\r\020\b\032\0020\tH\007¢\006\002\b\037J\030\020 \032\024\022\004\022\0020\003\022\n\022\b\022\004\022\0020\0030\"0!J\b\020#\032\0020\003H\026J\016\020$\032\0020\0032\006\020\032\032\0020\tJ\024\020%\032\b\022\004\022\0020\0030\"2\006\020\022\032\0020\003R\026\020\004\032\b\022\004\022\0020\0030\005X\004¢\006\004\n\002\020\007R\021\020\b\032\0020\t8G¢\006\006\032\004\b\b\020\n¨\006("}, d2 = {"Lokhttp3/Headers;", "", "Lkotlin/Pair;", "", "namesAndValues", "", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "size", "", "()I", "byteCount", "", "equals", "", "other", "", "get", "name", "getDate", "Ljava/util/Date;", "getInstant", "Ljava/time/Instant;", "hashCode", "iterator", "", "index", "names", "", "newBuilder", "Lokhttp3/Headers$Builder;", "-deprecated_size", "toMultimap", "", "", "toString", "value", "values", "Builder", "Companion", "okhttp"})
/*     */ public final class Headers
/*     */   implements Iterable<Pair<? extends String, ? extends String>>, KMappedMarker
/*     */ {
/*     */   private final String[] namesAndValues;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   private Headers(String[] namesAndValues) {
/*  51 */     this.namesAndValues = namesAndValues;
/*     */   }
/*     */   @Nullable
/*     */   public final String get(@NotNull String name) {
/*  55 */     Intrinsics.checkNotNullParameter(name, "name"); return Companion.get(this.namesAndValues, name);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public final Date getDate(@NotNull String name) {
/*  61 */     Intrinsics.checkNotNullParameter(name, "name"); get(name); return (get(name) != null) ? DatesKt.toHttpDateOrNull(get(name)) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @IgnoreJRERequirement
/*     */   @Nullable
/*     */   public final Instant getInstant(@NotNull String name) {
/*  69 */     Intrinsics.checkNotNullParameter(name, "name"); Date value = getDate(name);
/*  70 */     return (value != null) ? value.toInstant() : null;
/*     */   }
/*     */   
/*     */   @JvmName(name = "size")
/*     */   public final int size() {
/*  75 */     return this.namesAndValues.length / 2;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "size"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_size")
/*     */   public final int -deprecated_size() {
/*  82 */     return size();
/*     */   } @NotNull
/*     */   public final String name(int index) {
/*  85 */     return this.namesAndValues[index * 2];
/*     */   } @NotNull
/*     */   public final String value(int index) {
/*  88 */     return this.namesAndValues[index * 2 + 1];
/*     */   }
/*     */   @NotNull
/*     */   public final Set<String> names() {
/*  92 */     TreeSet<String> result = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE)); byte b; int i;
/*  93 */     for (b = 0, i = size(); b < i; b++) {
/*  94 */       result.add(name(b));
/*     */     }
/*  96 */     Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableSet(result), "Collections.unmodifiableSet(result)"); return Collections.unmodifiableSet(result);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public final List<String> values(@NotNull String name) {
/* 101 */     Intrinsics.checkNotNullParameter(name, "name"); List<String> result = (List)null; byte b; int i;
/* 102 */     for (b = 0, i = size(); b < i; b++) {
/* 103 */       if (StringsKt.equals(name, name(b), true)) {
/* 104 */         if (result == null) result = new ArrayList(2); 
/* 105 */         result.add(value(b));
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(result), "Collections.unmodifiableList(result)"); return (result != null) ? Collections.unmodifiableList(result) : 
/*     */       
/* 111 */       CollectionsKt.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final long byteCount()
/*     */   {
/* 123 */     long result = (this.namesAndValues.length * 2); byte b;
/*     */     int i;
/* 125 */     for (b = 0, i = this.namesAndValues.length; b < i; b++) {
/* 126 */       result += this.namesAndValues[b].length();
/*     */     }
/*     */     
/* 129 */     return result; } @NotNull
/*     */   public Iterator<Pair<String, String>> iterator() { int i;
/*     */     Pair[] arrayOfPair;
/*     */     byte b;
/* 133 */     for (i = size(), arrayOfPair = new Pair[i], b = 0; b < i; ) { byte b1 = b, b2 = b; Pair[] arrayOfPair1 = arrayOfPair; int $i$a$-<init>-Headers$iterator$1 = 0; Pair pair = TuplesKt.to(name(b1), value(b1)); arrayOfPair1[b2] = pair; b++; }  return ArrayIteratorKt.iterator((Object[])arrayOfPair); }
/*     */   
/*     */   @NotNull
/*     */   public final Builder newBuilder() {
/* 137 */     Builder result = new Builder();
/* 138 */     List<String> list = result.getNamesAndValues$okhttp(); String[] arrayOfString = this.namesAndValues; boolean bool = false; CollectionsKt.addAll(list, (Object[])arrayOfString);
/* 139 */     return result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(@Nullable Object other) {
/* 179 */     if (other instanceof Headers) { String[] arrayOfString1 = this.namesAndValues, arrayOfString2 = ((Headers)other).namesAndValues; boolean bool = false; if (Arrays.equals((Object[])arrayOfString1, (Object[])arrayOfString2)); }  return false;
/*     */   }
/*     */   public int hashCode() {
/* 182 */     String[] arrayOfString = this.namesAndValues; boolean bool = false; return Arrays.hashCode((Object[])arrayOfString);
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
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String toString() {
/* 198 */     boolean bool1 = false, bool2 = false; StringBuilder stringBuilder1 = new StringBuilder(); boolean bool3 = false, bool4 = false; StringBuilder $this$buildString = stringBuilder1; int $i$a$-buildString-Headers$toString$1 = 0; byte b; int i;
/* 199 */     for (b = 0, i = size(); b < i; b++) {
/* 200 */       String name = name(b);
/* 201 */       String value = value(b);
/* 202 */       $this$buildString.append(name);
/* 203 */       $this$buildString.append(": ");
/* 204 */       $this$buildString.append(Util.isSensitiveHeader(name) ? "██" : value);
/* 205 */       $this$buildString.append("\n");
/*     */     } 
/*     */     Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
/*     */     return stringBuilder1.toString();
/*     */   }
/*     */   @NotNull
/* 211 */   public final Map<String, List<String>> toMultimap() { TreeMap<Object, Object> result = new TreeMap<>(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE)); byte b; int i;
/* 212 */     for (b = 0, i = size(); b < i; b++) {
/* 213 */       String str1 = name(b); Intrinsics.checkNotNullExpressionValue(Locale.US, "Locale.US"); Locale locale = Locale.US; boolean bool = false; if (str1 == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str1.toLowerCase(locale), "(this as java.lang.String).toLowerCase(locale)"); String name = str1.toLowerCase(locale);
/* 214 */       List<String> values = (List)result.get(name);
/* 215 */       if (values == null) {
/* 216 */         values = new ArrayList(2);
/* 217 */         result.put(name, values);
/*     */       } 
/* 219 */       values.add(value(b));
/*     */     } 
/* 221 */     return (Map)result; } @JvmStatic @JvmName(name = "of") @NotNull public static final Headers of(@NotNull String... namesAndValues) { return Companion.of(namesAndValues); }
/*     */   @JvmStatic @JvmName(name = "of") @NotNull public static final Headers of(@NotNull Map<String, String> $this$toHeaders) { return Companion.of($this$toHeaders); }
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000,\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020!\n\002\020\016\n\002\b\006\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\b\030\0002\0020\001B\005¢\006\002\020\002J\016\020\b\032\0020\0002\006\020\t\032\0020\005J\030\020\b\032\0020\0002\006\020\n\032\0020\0052\006\020\013\032\0020\fH\007J\026\020\b\032\0020\0002\006\020\n\032\0020\0052\006\020\013\032\0020\rJ\026\020\b\032\0020\0002\006\020\n\032\0020\0052\006\020\013\032\0020\005J\016\020\016\032\0020\0002\006\020\017\032\0020\020J\025\020\021\032\0020\0002\006\020\t\032\0020\005H\000¢\006\002\b\022J\035\020\021\032\0020\0002\006\020\n\032\0020\0052\006\020\013\032\0020\005H\000¢\006\002\b\022J\026\020\023\032\0020\0002\006\020\n\032\0020\0052\006\020\013\032\0020\005J\006\020\024\032\0020\020J\023\020\025\032\004\030\0010\0052\006\020\n\032\0020\005H\002J\016\020\026\032\0020\0002\006\020\n\032\0020\005J\031\020\027\032\0020\0002\006\020\n\032\0020\0052\006\020\013\032\0020\fH\002J\031\020\027\032\0020\0002\006\020\n\032\0020\0052\006\020\013\032\0020\rH\002J\031\020\027\032\0020\0002\006\020\n\032\0020\0052\006\020\013\032\0020\005H\002R\032\020\003\032\b\022\004\022\0020\0050\004X\004¢\006\b\n\000\032\004\b\006\020\007¨\006\030"}, d2 = {"Lokhttp3/Headers$Builder;", "", "()V", "namesAndValues", "", "", "getNamesAndValues$okhttp", "()Ljava/util/List;", "add", "line", "name", "value", "Ljava/time/Instant;", "Ljava/util/Date;", "addAll", "headers", "Lokhttp3/Headers;", "addLenient", "addLenient$okhttp", "addUnsafeNonAscii", "build", "get", "removeAll", "set", "okhttp"})
/*     */   public static final class Builder { @NotNull
/* 225 */     private final List<String> namesAndValues = new ArrayList<>(20); @NotNull public final List<String> getNamesAndValues$okhttp() { return this.namesAndValues; }
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public final Builder addLenient$okhttp(@NotNull String line) {
/* 231 */       Intrinsics.checkNotNullParameter(line, "line"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Headers$Builder$addLenient$1 = 0;
/* 232 */       int index = StringsKt.indexOf$default(line, ':', 1, false, 4, null);
/*     */       
/* 234 */       if (index != -1) {
/* 235 */         String str = line; int i = 0; boolean bool = false; Intrinsics.checkNotNullExpressionValue(str.substring(i, index), "(this as java.lang.Strin…ing(startIndex, endIndex)"); str = line; i = index + 1; bool = false; Intrinsics.checkNotNullExpressionValue(str.substring(i), "(this as java.lang.String).substring(startIndex)"); $this$apply.addLenient$okhttp(str.substring(i, index), str.substring(i));
/*     */       }
/* 237 */       else if (line.charAt(0) == ':') {
/*     */ 
/*     */         
/* 240 */         String str = line; boolean bool3 = true, bool4 = false; Intrinsics.checkNotNullExpressionValue(str.substring(bool3), "(this as java.lang.String).substring(startIndex)"); $this$apply.addLenient$okhttp("", str.substring(bool3));
/*     */       }
/*     */       else {
/*     */         
/* 244 */         $this$apply.addLenient$okhttp("", line);
/*     */       } 
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/* 250 */     public final Builder add(@NotNull String line) { Intrinsics.checkNotNullParameter(line, "line"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-Headers$Builder$add$1 = 0;
/* 251 */       int index = StringsKt.indexOf$default(line, ':', 0, false, 6, null);
/* 252 */       boolean bool3 = (index != -1) ? true : false; int i = 0; boolean bool4 = false; if (!bool3)
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
/* 459 */         int $i$a$-require-Headers$Builder$add$1$1 = 0; String str1 = "Unexpected header: " + line; throw (Throwable)new IllegalArgumentException(str1.toString()); }  String str = line; i = 0; bool4 = false; Intrinsics.checkNotNullExpressionValue(str.substring(i, index), "(this as java.lang.Strin…ing(startIndex, endIndex)"); str = str.substring(i, index); i = 0; if (str == null)
/* 460 */         throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");  str = line; i = index + 1; bool4 = false; Intrinsics.checkNotNullExpressionValue(str.substring(i), "(this as java.lang.String).substring(startIndex)"); $this$apply.add(StringsKt.trim(str).toString(), str.substring(i)); return builder1; } @NotNull public final Headers build() { Collection<String> $this$toTypedArray$iv = this.namesAndValues; int $i$f$toTypedArray = 0; Collection<String> thisCollection$iv = $this$toTypedArray$iv;
/* 461 */       if (thisCollection$iv.toArray(new String[0]) == null) throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");  return new Headers(thisCollection$iv.toArray(new String[0]), null); }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public final Builder add(@NotNull String name, @NotNull String value) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       Intrinsics.checkNotNullParameter(value, "value");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Headers$Builder$add$2 = 0;
/*     */       Headers.Companion.checkName(name);
/*     */       Headers.Companion.checkValue(value, name);
/*     */       $this$apply.addLenient$okhttp(name, value);
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder addUnsafeNonAscii(@NotNull String name, @NotNull String value) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       Intrinsics.checkNotNullParameter(value, "value");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Headers$Builder$addUnsafeNonAscii$1 = 0;
/*     */       Headers.Companion.checkName(name);
/*     */       $this$apply.addLenient$okhttp(name, value);
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder addAll(@NotNull Headers headers) {
/*     */       Intrinsics.checkNotNullParameter(headers, "headers");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Headers$Builder$addAll$1 = 0;
/*     */       byte b;
/*     */       int i;
/*     */       for (b = 0, i = headers.size(); b < i; b++)
/*     */         $this$apply.addLenient$okhttp(headers.name(b), headers.value(b)); 
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder add(@NotNull String name, @NotNull Date value) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       Intrinsics.checkNotNullParameter(value, "value");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Headers$Builder$add$3 = 0;
/*     */       $this$apply.add(name, DatesKt.toHttpDateString(value));
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @IgnoreJRERequirement
/*     */     @NotNull
/*     */     public final Builder add(@NotNull String name, @NotNull Instant value) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       Intrinsics.checkNotNullParameter(value, "value");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Headers$Builder$add$4 = 0;
/*     */       $this$apply.add(name, new Date(value.toEpochMilli()));
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder set(@NotNull String name, @NotNull Date value) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       Intrinsics.checkNotNullParameter(value, "value");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Headers$Builder$set$1 = 0;
/*     */       $this$apply.set(name, DatesKt.toHttpDateString(value));
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @IgnoreJRERequirement
/*     */     @NotNull
/*     */     public final Builder set(@NotNull String name, @NotNull Instant value) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       Intrinsics.checkNotNullParameter(value, "value");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Headers$Builder$set$2 = 0;
/*     */       return $this$apply.set(name, new Date(value.toEpochMilli()));
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder addLenient$okhttp(@NotNull String name, @NotNull String value) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       Intrinsics.checkNotNullParameter(value, "value");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Headers$Builder$addLenient$2 = 0;
/*     */       $this$apply.namesAndValues.add(name);
/*     */       String str = value;
/*     */       boolean bool3 = false;
/*     */       $this$apply.namesAndValues.add(StringsKt.trim(str).toString());
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder removeAll(@NotNull String name) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Headers$Builder$removeAll$1 = 0;
/*     */       int i = 0;
/*     */       while (i < $this$apply.namesAndValues.size()) {
/*     */         if (StringsKt.equals(name, $this$apply.namesAndValues.get(i), true)) {
/*     */           $this$apply.namesAndValues.remove(i);
/*     */           $this$apply.namesAndValues.remove(i);
/*     */           i -= 2;
/*     */         } 
/*     */         i += 2;
/*     */       } 
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder set(@NotNull String name, @NotNull String value) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       Intrinsics.checkNotNullParameter(value, "value");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-Headers$Builder$set$3 = 0;
/*     */       Headers.Companion.checkName(name);
/*     */       Headers.Companion.checkValue(value, name);
/*     */       $this$apply.removeAll(name);
/*     */       $this$apply.addLenient$okhttp(name, value);
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public final String get(@NotNull String name) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       int i = RangesKt.step(RangesKt.downTo(this.namesAndValues.size() - 2, 0), 2).getFirst(), j = RangesKt.step(RangesKt.downTo(this.namesAndValues.size() - 2, 0), 2).getLast(), k = RangesKt.step(RangesKt.downTo(this.namesAndValues.size() - 2, 0), 2).getStep();
/*     */       if ((k >= 0) ? (i <= j) : (i >= j))
/*     */         while (true) {
/*     */           if (StringsKt.equals(name, this.namesAndValues.get(i), true))
/*     */             return this.namesAndValues.get(i + 1); 
/*     */           if (i != j) {
/*     */             int m = i + k;
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         }  
/*     */       return null;
/*     */     } }
/*     */ 
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\002\n\000\n\002\020\016\n\002\b\004\n\002\020\021\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020$\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\020\020\003\032\0020\0042\006\020\005\032\0020\006H\002J\030\020\007\032\0020\0042\006\020\b\032\0020\0062\006\020\005\032\0020\006H\002J%\020\t\032\004\030\0010\0062\f\020\n\032\b\022\004\022\0020\0060\0132\006\020\005\032\0020\006H\002¢\006\002\020\fJ#\020\r\032\0020\0162\022\020\n\032\n\022\006\b\001\022\0020\0060\013\"\0020\006H\007¢\006\004\b\017\020\020J#\020\017\032\0020\0162\022\020\n\032\n\022\006\b\001\022\0020\0060\013\"\0020\006H\007¢\006\004\b\021\020\020J!\020\017\032\0020\0162\022\020\022\032\016\022\004\022\0020\006\022\004\022\0020\0060\023H\007¢\006\002\b\021J\035\020\024\032\0020\016*\016\022\004\022\0020\006\022\004\022\0020\0060\023H\007¢\006\002\b\017¨\006\025"}, d2 = {"Lokhttp3/Headers$Companion;", "", "()V", "checkName", "", "name", "", "checkValue", "value", "get", "namesAndValues", "", "([Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "headersOf", "Lokhttp3/Headers;", "of", "([Ljava/lang/String;)Lokhttp3/Headers;", "-deprecated_of", "headers", "", "toHeaders", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     private final String get(String[] namesAndValues, String name) {
/*     */       int i = RangesKt.step(RangesKt.downTo(namesAndValues.length - 2, 0), 2).getFirst(), j = RangesKt.step(RangesKt.downTo(namesAndValues.length - 2, 0), 2).getLast(), k = RangesKt.step(RangesKt.downTo(namesAndValues.length - 2, 0), 2).getStep();
/*     */       if ((k >= 0) ? (i <= j) : (i >= j))
/*     */         while (true) {
/*     */           if (StringsKt.equals(name, namesAndValues[i], true))
/*     */             return namesAndValues[i + 1]; 
/*     */           if (i != j) {
/*     */             int m = i + k;
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         }  
/*     */       return null;
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @JvmName(name = "of")
/*     */     @NotNull
/*     */     public final Headers of(@NotNull String... namesAndValues) {
/*     */       Intrinsics.checkNotNullParameter(namesAndValues, "namesAndValues");
/*     */       boolean bool = (namesAndValues.length % 2 == 0) ? true : false;
/*     */       byte b = 0;
/*     */       int j = 0;
/*     */       if (!bool) {
/*     */         int $i$a$-require-Headers$Companion$headersOf$1 = 0;
/*     */         String str = "Expected alternating header names and values";
/*     */         throw (Throwable)new IllegalArgumentException(str.toString());
/*     */       } 
/*     */       if (namesAndValues.clone() == null)
/*     */         throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>"); 
/*     */       String[] arrayOfString = (String[])namesAndValues.clone();
/*     */       for (b = 0, j = arrayOfString.length; b < j; b++) {
/*     */         boolean bool1 = (arrayOfString[b] != null) ? true : false, bool2 = false, bool3 = false;
/*     */         if (!bool1) {
/*     */           int $i$a$-require-Headers$Companion$headersOf$2 = 0;
/*     */           String str1 = "Headers cannot be null";
/*     */           throw (Throwable)new IllegalArgumentException(str1.toString());
/*     */         } 
/*     */         String str = arrayOfString[b];
/*     */         bool2 = false;
/*     */         if (str == null)
/*     */           throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence"); 
/*     */         arrayOfString[b] = StringsKt.trim(str).toString();
/*     */       } 
/*     */       int i = RangesKt.step((IntProgression)ArraysKt.getIndices((Object[])arrayOfString), 2).getFirst();
/*     */       j = RangesKt.step((IntProgression)ArraysKt.getIndices((Object[])arrayOfString), 2).getLast();
/*     */       int k = RangesKt.step((IntProgression)ArraysKt.getIndices((Object[])arrayOfString), 2).getStep();
/*     */       if ((k >= 0) ? (i <= j) : (i >= j))
/*     */         while (true) {
/*     */           String name = arrayOfString[i];
/*     */           String value = arrayOfString[i + 1];
/*     */           checkName(name);
/*     */           checkValue(value, name);
/*     */           if (i != j) {
/*     */             i += k;
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         }  
/*     */       return new Headers(arrayOfString, null);
/*     */     }
/*     */     
/*     */     @Deprecated(message = "function name changed", replaceWith = @ReplaceWith(imports = {}, expression = "headersOf(*namesAndValues)"), level = DeprecationLevel.ERROR)
/*     */     @JvmName(name = "-deprecated_of")
/*     */     @NotNull
/*     */     public final Headers -deprecated_of(@NotNull String... namesAndValues) {
/*     */       Intrinsics.checkNotNullParameter(namesAndValues, "namesAndValues");
/*     */       return of(Arrays.<String>copyOf(namesAndValues, namesAndValues.length));
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @JvmName(name = "of")
/*     */     @NotNull
/*     */     public final Headers of(@NotNull Map $this$toHeaders) {
/*     */       Intrinsics.checkNotNullParameter($this$toHeaders, "$this$toHeaders");
/*     */       String[] namesAndValues = new String[$this$toHeaders.size() * 2];
/*     */       int i = 0;
/*     */       Iterator<Map.Entry> iterator;
/*     */       Map map;
/*     */       boolean bool;
/*     */       for (map = $this$toHeaders, bool = false, iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
/*     */         Map.Entry entry1 = iterator.next(), entry2 = entry1;
/*     */         boolean bool1 = false;
/*     */         String str1 = (String)entry2.getKey();
/*     */         entry2 = entry1;
/*     */         bool1 = false;
/*     */         String v = (String)entry2.getValue();
/*     */         String str2 = str1;
/*     */         boolean bool2 = false;
/*     */         if (str2 == null)
/*     */           throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence"); 
/*     */         String name = StringsKt.trim(str2).toString();
/*     */         String str3 = v;
/*     */         boolean bool3 = false;
/*     */         if (str3 == null)
/*     */           throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence"); 
/*     */         String value = StringsKt.trim(str3).toString();
/*     */         checkName(name);
/*     */         checkValue(value, name);
/*     */         namesAndValues[i] = name;
/*     */         namesAndValues[i + 1] = value;
/*     */         i += 2;
/*     */       } 
/*     */       return new Headers(namesAndValues, null);
/*     */     }
/*     */     
/*     */     @Deprecated(message = "function moved to extension", replaceWith = @ReplaceWith(imports = {}, expression = "headers.toHeaders()"), level = DeprecationLevel.ERROR)
/*     */     @JvmName(name = "-deprecated_of")
/*     */     @NotNull
/*     */     public final Headers -deprecated_of(@NotNull Map<String, String> headers) {
/*     */       Intrinsics.checkNotNullParameter(headers, "headers");
/*     */       return of(headers);
/*     */     }
/*     */     
/*     */     private final void checkName(String name) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: checkcast java/lang/CharSequence
/*     */       //   4: astore_2
/*     */       //   5: iconst_0
/*     */       //   6: istore_3
/*     */       //   7: aload_2
/*     */       //   8: invokeinterface length : ()I
/*     */       //   13: ifle -> 20
/*     */       //   16: iconst_1
/*     */       //   17: goto -> 21
/*     */       //   20: iconst_0
/*     */       //   21: istore_2
/*     */       //   22: iconst_0
/*     */       //   23: istore_3
/*     */       //   24: iconst_0
/*     */       //   25: istore #4
/*     */       //   27: iload_2
/*     */       //   28: ifne -> 54
/*     */       //   31: iconst_0
/*     */       //   32: istore #5
/*     */       //   34: ldc 'name is empty'
/*     */       //   36: astore #4
/*     */       //   38: new java/lang/IllegalArgumentException
/*     */       //   41: dup
/*     */       //   42: aload #4
/*     */       //   44: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   47: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   50: checkcast java/lang/Throwable
/*     */       //   53: athrow
/*     */       //   54: iconst_0
/*     */       //   55: istore_2
/*     */       //   56: aload_1
/*     */       //   57: checkcast java/lang/CharSequence
/*     */       //   60: invokeinterface length : ()I
/*     */       //   65: istore_3
/*     */       //   66: iload_2
/*     */       //   67: iload_3
/*     */       //   68: if_icmpge -> 173
/*     */       //   71: aload_1
/*     */       //   72: iload_2
/*     */       //   73: invokevirtual charAt : (I)C
/*     */       //   76: istore #4
/*     */       //   78: bipush #126
/*     */       //   80: bipush #33
/*     */       //   82: iload #4
/*     */       //   84: istore #5
/*     */       //   86: iload #5
/*     */       //   88: if_icmple -> 95
/*     */       //   91: pop
/*     */       //   92: goto -> 104
/*     */       //   95: iload #5
/*     */       //   97: if_icmplt -> 104
/*     */       //   100: iconst_1
/*     */       //   101: goto -> 105
/*     */       //   104: iconst_0
/*     */       //   105: istore #5
/*     */       //   107: iconst_0
/*     */       //   108: istore #6
/*     */       //   110: iconst_0
/*     */       //   111: istore #7
/*     */       //   113: iload #5
/*     */       //   115: ifne -> 167
/*     */       //   118: iconst_0
/*     */       //   119: istore #8
/*     */       //   121: ldc 'Unexpected char %#04x at %d in header name: %s'
/*     */       //   123: iconst_3
/*     */       //   124: anewarray java/lang/Object
/*     */       //   127: dup
/*     */       //   128: iconst_0
/*     */       //   129: iload #4
/*     */       //   131: invokestatic valueOf : (I)Ljava/lang/Integer;
/*     */       //   134: aastore
/*     */       //   135: dup
/*     */       //   136: iconst_1
/*     */       //   137: iload_2
/*     */       //   138: invokestatic valueOf : (I)Ljava/lang/Integer;
/*     */       //   141: aastore
/*     */       //   142: dup
/*     */       //   143: iconst_2
/*     */       //   144: aload_1
/*     */       //   145: aastore
/*     */       //   146: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
/*     */       //   149: astore #7
/*     */       //   151: new java/lang/IllegalArgumentException
/*     */       //   154: dup
/*     */       //   155: aload #7
/*     */       //   157: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   160: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   163: checkcast java/lang/Throwable
/*     */       //   166: athrow
/*     */       //   167: iinc #2, 1
/*     */       //   170: goto -> 66
/*     */       //   173: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #438	-> 0
/*     */       //   #438	-> 22
/*     */       //   #459	-> 31
/*     */       //   #438	-> 34
/*     */       //   #438	-> 36
/*     */       //   #439	-> 54
/*     */       //   #439	-> 66
/*     */       //   #440	-> 71
/*     */       //   #441	-> 78
/*     */       //   #442	-> 121
/*     */       //   #441	-> 149
/*     */       //   #439	-> 167
/*     */       //   #445	-> 173
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   34	2	5	$i$a$-require-Headers$Companion$checkName$1	I
/*     */       //   121	28	8	$i$a$-require-Headers$Companion$checkName$2	I
/*     */       //   78	89	4	c	C
/*     */       //   71	99	2	i	I
/*     */       //   0	174	0	this	Lokhttp3/Headers$Companion;
/*     */       //   0	174	1	name	Ljava/lang/String;
/*     */     }
/*     */     
/*     */     private final void checkValue(String value, String name) {
/*     */       // Byte code:
/*     */       //   0: iconst_0
/*     */       //   1: istore_3
/*     */       //   2: aload_1
/*     */       //   3: checkcast java/lang/CharSequence
/*     */       //   6: invokeinterface length : ()I
/*     */       //   11: istore #4
/*     */       //   13: iload_3
/*     */       //   14: iload #4
/*     */       //   16: if_icmpge -> 175
/*     */       //   19: aload_1
/*     */       //   20: iload_3
/*     */       //   21: invokevirtual charAt : (I)C
/*     */       //   24: istore #5
/*     */       //   26: iload #5
/*     */       //   28: bipush #9
/*     */       //   30: if_icmpeq -> 55
/*     */       //   33: bipush #126
/*     */       //   35: bipush #32
/*     */       //   37: iload #5
/*     */       //   39: istore #6
/*     */       //   41: iload #6
/*     */       //   43: if_icmple -> 50
/*     */       //   46: pop
/*     */       //   47: goto -> 59
/*     */       //   50: iload #6
/*     */       //   52: if_icmplt -> 59
/*     */       //   55: iconst_1
/*     */       //   56: goto -> 60
/*     */       //   59: iconst_0
/*     */       //   60: istore #6
/*     */       //   62: iconst_0
/*     */       //   63: istore #7
/*     */       //   65: iconst_0
/*     */       //   66: istore #8
/*     */       //   68: iload #6
/*     */       //   70: ifne -> 169
/*     */       //   73: iconst_0
/*     */       //   74: istore #9
/*     */       //   76: new java/lang/StringBuilder
/*     */       //   79: dup
/*     */       //   80: invokespecial <init> : ()V
/*     */       //   83: ldc 'Unexpected char %#04x at %d in %s value'
/*     */       //   85: iconst_3
/*     */       //   86: anewarray java/lang/Object
/*     */       //   89: dup
/*     */       //   90: iconst_0
/*     */       //   91: iload #5
/*     */       //   93: invokestatic valueOf : (I)Ljava/lang/Integer;
/*     */       //   96: aastore
/*     */       //   97: dup
/*     */       //   98: iconst_1
/*     */       //   99: iload_3
/*     */       //   100: invokestatic valueOf : (I)Ljava/lang/Integer;
/*     */       //   103: aastore
/*     */       //   104: dup
/*     */       //   105: iconst_2
/*     */       //   106: aload_2
/*     */       //   107: aastore
/*     */       //   108: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
/*     */       //   111: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */       //   114: aload_2
/*     */       //   115: invokestatic isSensitiveHeader : (Ljava/lang/String;)Z
/*     */       //   118: ifeq -> 126
/*     */       //   121: ldc ''
/*     */       //   123: goto -> 145
/*     */       //   126: new java/lang/StringBuilder
/*     */       //   129: dup
/*     */       //   130: invokespecial <init> : ()V
/*     */       //   133: ldc ': '
/*     */       //   135: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */       //   138: aload_1
/*     */       //   139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */       //   142: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   145: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */       //   148: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   151: astore #8
/*     */       //   153: new java/lang/IllegalArgumentException
/*     */       //   156: dup
/*     */       //   157: aload #8
/*     */       //   159: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   162: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   165: checkcast java/lang/Throwable
/*     */       //   168: athrow
/*     */       //   169: iinc #3, 1
/*     */       //   172: goto -> 13
/*     */       //   175: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #448	-> 0
/*     */       //   #448	-> 13
/*     */       //   #449	-> 19
/*     */       //   #450	-> 26
/*     */       //   #451	-> 76
/*     */       //   #452	-> 114
/*     */       //   #450	-> 151
/*     */       //   #448	-> 169
/*     */       //   #455	-> 175
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   76	75	9	$i$a$-require-Headers$Companion$checkValue$1	I
/*     */       //   26	143	5	c	C
/*     */       //   19	153	3	i	I
/*     */       //   0	176	0	this	Lokhttp3/Headers$Companion;
/*     */       //   0	176	1	value	Ljava/lang/String;
/*     */       //   0	176	2	name	Ljava/lang/String;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Headers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */