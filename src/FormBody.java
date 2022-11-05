/*     */ package okhttp3;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.JvmOverloads;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSink;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000@\n\002\030\002\n\002\030\002\n\000\n\002\020 \n\002\020\016\n\002\b\003\n\002\020\b\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\002\b\b\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\002\n\002\b\003\030\000 \0342\0020\001:\002\033\034B#\b\000\022\f\020\002\032\b\022\004\022\0020\0040\003\022\f\020\005\032\b\022\004\022\0020\0040\003¢\006\002\020\006J\b\020\n\032\0020\013H\026J\b\020\f\032\0020\rH\026J\016\020\016\032\0020\0042\006\020\017\032\0020\bJ\016\020\020\032\0020\0042\006\020\017\032\0020\bJ\016\020\021\032\0020\0042\006\020\017\032\0020\bJ\r\020\007\032\0020\bH\007¢\006\002\b\022J\016\020\023\032\0020\0042\006\020\017\032\0020\bJ\032\020\024\032\0020\0132\b\020\025\032\004\030\0010\0262\006\020\027\032\0020\030H\002J\020\020\031\032\0020\0322\006\020\025\032\0020\026H\026R\024\020\002\032\b\022\004\022\0020\0040\003X\004¢\006\002\n\000R\024\020\005\032\b\022\004\022\0020\0040\003X\004¢\006\002\n\000R\021\020\007\032\0020\b8G¢\006\006\032\004\b\007\020\t¨\006\035"}, d2 = {"Lokhttp3/FormBody;", "Lokhttp3/RequestBody;", "encodedNames", "", "", "encodedValues", "(Ljava/util/List;Ljava/util/List;)V", "size", "", "()I", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "encodedName", "index", "encodedValue", "name", "-deprecated_size", "value", "writeOrCountBytes", "sink", "Lokio/BufferedSink;", "countBytes", "", "writeTo", "", "Builder", "Companion", "okhttp"})
/*     */ public final class FormBody
/*     */   extends RequestBody
/*     */ {
/*     */   private final List<String> encodedNames;
/*     */   private final List<String> encodedValues;
/*     */   private static final MediaType CONTENT_TYPE;
/*     */   
/*     */   public FormBody(@NotNull List encodedNames, @NotNull List encodedValues) {
/*  32 */     this.encodedNames = Util.toImmutableList(encodedNames);
/*  33 */     this.encodedValues = Util.toImmutableList(encodedValues);
/*     */   }
/*     */   @JvmName(name = "size")
/*     */   public final int size() {
/*  37 */     return this.encodedNames.size();
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "size"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_size")
/*     */   public final int -deprecated_size()
/*     */   {
/*  44 */     return size();
/*     */   } @NotNull
/*  46 */   public final String encodedName(int index) { return this.encodedNames.get(index); }
/*     */   @NotNull
/*  48 */   public final String name(int index) { return HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, encodedName(index), 0, 0, true, 3, null); }
/*     */   @NotNull
/*  50 */   public final String encodedValue(int index) { return this.encodedValues.get(index); }
/*     */   @NotNull
/*  52 */   public final String value(int index) { return HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, encodedValue(index), 0, 0, true, 3, null); } @NotNull
/*     */   public MediaType contentType() {
/*  54 */     return CONTENT_TYPE;
/*     */   } public long contentLength() {
/*  56 */     return writeOrCountBytes(null, true);
/*     */   }
/*     */   
/*     */   public void writeTo(@NotNull BufferedSink sink) throws IOException {
/*  60 */     Intrinsics.checkNotNullParameter(sink, "sink"); writeOrCountBytes(sink, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final long writeOrCountBytes(BufferedSink sink, boolean countBytes) {
/*  70 */     long byteCount = 0L;
/*  71 */     Intrinsics.checkNotNull(sink); Buffer buffer = countBytes ? new Buffer() : sink.getBuffer(); byte b;
/*     */     int i;
/*  73 */     for (b = 0, i = this.encodedNames.size(); b < i; b++) {
/*  74 */       if (b > 0) buffer.writeByte(38); 
/*  75 */       buffer.writeUtf8(this.encodedNames.get(b));
/*  76 */       buffer.writeByte(61);
/*  77 */       buffer.writeUtf8(this.encodedValues.get(b));
/*     */     } 
/*     */     
/*  80 */     if (countBytes) {
/*  81 */       byteCount = buffer.size();
/*  82 */       buffer.clear();
/*     */     } 
/*     */     
/*  85 */     return byteCount;
/*     */   } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000$\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\002\n\002\020!\n\002\020\016\n\002\b\006\n\002\030\002\n\000\030\0002\0020\001B\023\b\007\022\n\b\002\020\002\032\004\030\0010\003¢\006\002\020\004J\026\020\t\032\0020\0002\006\020\n\032\0020\0072\006\020\013\032\0020\007J\026\020\f\032\0020\0002\006\020\n\032\0020\0072\006\020\013\032\0020\007J\006\020\r\032\0020\016R\020\020\002\032\004\030\0010\003X\004¢\006\002\n\000R\024\020\005\032\b\022\004\022\0020\0070\006X\004¢\006\002\n\000R\024\020\b\032\b\022\004\022\0020\0070\006X\004¢\006\002\n\000¨\006\017"}, d2 = {"Lokhttp3/FormBody$Builder;", "", "charset", "Ljava/nio/charset/Charset;", "(Ljava/nio/charset/Charset;)V", "names", "", "", "values", "add", "name", "value", "addEncoded", "build", "Lokhttp3/FormBody;", "okhttp"})
/*     */   public static final class Builder { private final List<String> names; @JvmOverloads
/*  88 */     public Builder(@Nullable Charset charset) { this.charset = charset;
/*  89 */       boolean bool = false; this.names = new ArrayList<>();
/*  90 */       bool = false; this.values = new ArrayList<>(); } private final List<String> values; private final Charset charset; @NotNull
/*     */     public final Builder add(@NotNull String name, @NotNull String value) {
/*  92 */       Intrinsics.checkNotNullParameter(name, "name"); Intrinsics.checkNotNullParameter(value, "value"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-FormBody$Builder$add$1 = 0;
/*  93 */       List<String> list = $this$apply.names; String str = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, 
/*  94 */           " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, 
/*  95 */           true, false, 
/*  96 */           $this$apply.charset, 91, null); boolean bool3 = false;
/*     */       list.add(str);
/*  98 */       list = $this$apply.values; str = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, value, 0, 0, 
/*  99 */           " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, 
/* 100 */           true, false, 
/* 101 */           $this$apply.charset, 91, null);
/*     */       bool3 = false;
/*     */       list.add(str);
/*     */       return builder1; } @NotNull
/* 105 */     public final Builder addEncoded(@NotNull String name, @NotNull String value) { Intrinsics.checkNotNullParameter(name, "name"); Intrinsics.checkNotNullParameter(value, "value"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-FormBody$Builder$addEncoded$1 = 0;
/* 106 */       List<String> list = $this$apply.names; String str = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, 
/* 107 */           " \"':;<=>@[]^`{}|/\\?#&!$(),~", 
/* 108 */           true, false, 
/* 109 */           true, false, 
/* 110 */           $this$apply.charset, 83, null); boolean bool3 = false;
/*     */       list.add(str);
/* 112 */       list = $this$apply.values; str = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, value, 0, 0, 
/* 113 */           " \"':;<=>@[]^`{}|/\\?#&!$(),~", 
/* 114 */           true, false, 
/* 115 */           true, false, 
/* 116 */           $this$apply.charset, 83, null);
/*     */       bool3 = false;
/*     */       list.add(str);
/*     */       return builder1; } @NotNull
/* 120 */     public final FormBody build() { return new FormBody(this.names, this.values); }
/*     */      @JvmOverloads
/*     */     public Builder() {
/*     */       this(null, 1, null);
/* 124 */     } } public static final Companion Companion = new Companion(null); @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004X\004¢\006\002\n\000¨\006\005"}, d2 = {"Lokhttp3/FormBody$Companion;", "", "()V", "CONTENT_TYPE", "Lokhttp3/MediaType;", "okhttp"}) public static final class Companion { private Companion() {} } static { CONTENT_TYPE = MediaType.Companion.get("application/x-www-form-urlencoded"); }
/*     */ 
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/FormBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */