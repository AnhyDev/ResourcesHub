/*      */ package okhttp3;
/*      */ 
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URI;
/*      */ import java.net.URISyntaxException;
/*      */ import java.net.URL;
/*      */ import java.nio.charset.Charset;
/*      */ import java.nio.charset.StandardCharsets;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import kotlin.Deprecated;
/*      */ import kotlin.DeprecationLevel;
/*      */ import kotlin.Metadata;
/*      */ import kotlin.ReplaceWith;
/*      */ import kotlin.collections.CollectionsKt;
/*      */ import kotlin.collections.SetsKt;
/*      */ import kotlin.jvm.JvmName;
/*      */ import kotlin.jvm.JvmStatic;
/*      */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*      */ import kotlin.jvm.internal.Intrinsics;
/*      */ import kotlin.ranges.IntProgression;
/*      */ import kotlin.ranges.RangesKt;
/*      */ import kotlin.text.Regex;
/*      */ import kotlin.text.StringsKt;
/*      */ import okhttp3.internal.HostnamesKt;
/*      */ import okhttp3.internal.Util;
/*      */ import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
/*      */ import okio.Buffer;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000H\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\004\n\002\020\b\n\000\n\002\020 \n\002\b\r\n\002\020\013\n\002\b\005\n\002\020\"\n\002\b\016\n\002\030\002\n\002\b\023\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\007\030\000 J2\0020\001:\002IJBa\b\000\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\006\020\005\032\0020\003\022\006\020\006\032\0020\003\022\006\020\007\032\0020\b\022\f\020\t\032\b\022\004\022\0020\0030\n\022\020\020\013\032\f\022\006\022\004\030\0010\003\030\0010\n\022\b\020\f\032\004\030\0010\003\022\006\020\r\032\0020\003¢\006\002\020\016J\017\020\017\032\004\030\0010\003H\007¢\006\002\b!J\r\020\021\032\0020\003H\007¢\006\002\b\"J\r\020\022\032\0020\003H\007¢\006\002\b#J\023\020\023\032\b\022\004\022\0020\0030\nH\007¢\006\002\b$J\017\020\025\032\004\030\0010\003H\007¢\006\002\b%J\r\020\026\032\0020\003H\007¢\006\002\b&J\023\020'\032\0020\0302\b\020(\032\004\030\0010\001H\002J\017\020\f\032\004\030\0010\003H\007¢\006\002\b)J\b\020*\032\0020\bH\026J\r\020\006\032\0020\003H\007¢\006\002\b+J\006\020,\032\0020-J\020\020,\032\004\030\0010-2\006\020.\032\0020\003J\r\020\005\032\0020\003H\007¢\006\002\b/J\023\020\t\032\b\022\004\022\0020\0030\nH\007¢\006\002\b0J\r\020\032\032\0020\bH\007¢\006\002\b1J\r\020\007\032\0020\bH\007¢\006\002\b2J\017\020\034\032\004\030\0010\003H\007¢\006\002\b3J\020\0204\032\004\030\0010\0032\006\0205\032\0020\003J\016\0206\032\0020\0032\006\0207\032\0020\bJ\023\020\035\032\b\022\004\022\0020\0030\036H\007¢\006\002\b8J\020\0209\032\004\030\0010\0032\006\0207\032\0020\bJ\026\020:\032\n\022\006\022\004\030\0010\0030\n2\006\0205\032\0020\003J\r\020 \032\0020\bH\007¢\006\002\b;J\006\020<\032\0020\003J\020\020=\032\004\030\0010\0002\006\020.\032\0020\003J\r\020\002\032\0020\003H\007¢\006\002\b>J\b\020?\032\0020\003H\026J\r\020@\032\0020AH\007¢\006\002\bBJ\r\020C\032\0020DH\007¢\006\002\b\rJ\b\020E\032\004\030\0010\003J\r\020B\032\0020AH\007¢\006\002\bFJ\r\020\r\032\0020DH\007¢\006\002\bGJ\r\020\004\032\0020\003H\007¢\006\002\bHR\023\020\017\032\004\030\0010\0038G¢\006\006\032\004\b\017\020\020R\021\020\021\032\0020\0038G¢\006\006\032\004\b\021\020\020R\021\020\022\032\0020\0038G¢\006\006\032\004\b\022\020\020R\027\020\023\032\b\022\004\022\0020\0030\n8G¢\006\006\032\004\b\023\020\024R\023\020\025\032\004\030\0010\0038G¢\006\006\032\004\b\025\020\020R\021\020\026\032\0020\0038G¢\006\006\032\004\b\026\020\020R\025\020\f\032\004\030\0010\0038\007¢\006\b\n\000\032\004\b\f\020\020R\023\020\006\032\0020\0038\007¢\006\b\n\000\032\004\b\006\020\020R\021\020\027\032\0020\030¢\006\b\n\000\032\004\b\027\020\031R\023\020\005\032\0020\0038\007¢\006\b\n\000\032\004\b\005\020\020R\031\020\t\032\b\022\004\022\0020\0030\n8\007¢\006\b\n\000\032\004\b\t\020\024R\021\020\032\032\0020\b8G¢\006\006\032\004\b\032\020\033R\023\020\007\032\0020\b8\007¢\006\b\n\000\032\004\b\007\020\033R\023\020\034\032\004\030\0010\0038G¢\006\006\032\004\b\034\020\020R\030\020\013\032\f\022\006\022\004\030\0010\003\030\0010\nX\004¢\006\002\n\000R\027\020\035\032\b\022\004\022\0020\0030\0368G¢\006\006\032\004\b\035\020\037R\021\020 \032\0020\b8G¢\006\006\032\004\b \020\033R\023\020\002\032\0020\0038\007¢\006\b\n\000\032\004\b\002\020\020R\016\020\r\032\0020\003X\004¢\006\002\n\000R\023\020\004\032\0020\0038\007¢\006\b\n\000\032\004\b\004\020\020¨\006K"}, d2 = {"Lokhttp3/HttpUrl;", "", "scheme", "", "username", "password", "host", "port", "", "pathSegments", "", "queryNamesAndValues", "fragment", "url", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "encodedFragment", "()Ljava/lang/String;", "encodedPassword", "encodedPath", "encodedPathSegments", "()Ljava/util/List;", "encodedQuery", "encodedUsername", "isHttps", "", "()Z", "pathSize", "()I", "query", "queryParameterNames", "", "()Ljava/util/Set;", "querySize", "-deprecated_encodedFragment", "-deprecated_encodedPassword", "-deprecated_encodedPath", "-deprecated_encodedPathSegments", "-deprecated_encodedQuery", "-deprecated_encodedUsername", "equals", "other", "-deprecated_fragment", "hashCode", "-deprecated_host", "newBuilder", "Lokhttp3/HttpUrl$Builder;", "link", "-deprecated_password", "-deprecated_pathSegments", "-deprecated_pathSize", "-deprecated_port", "-deprecated_query", "queryParameter", "name", "queryParameterName", "index", "-deprecated_queryParameterNames", "queryParameterValue", "queryParameterValues", "-deprecated_querySize", "redact", "resolve", "-deprecated_scheme", "toString", "toUri", "Ljava/net/URI;", "uri", "toUrl", "Ljava/net/URL;", "topPrivateDomain", "-deprecated_uri", "-deprecated_url", "-deprecated_username", "Builder", "Companion", "okhttp"})
/*      */ public final class HttpUrl
/*      */ {
/*      */   private final boolean isHttps;
/*      */   @NotNull
/*      */   private final String scheme;
/*      */   @NotNull
/*      */   private final String username;
/*      */   @NotNull
/*      */   private final String password;
/*      */   @NotNull
/*      */   private final String host;
/*      */   private final int port;
/*      */   @NotNull
/*      */   private final List<String> pathSegments;
/*      */   private final List<String> queryNamesAndValues;
/*      */   @Nullable
/*      */   private final String fragment;
/*      */   private final String url;
/*      */   private static final char[] HEX_DIGITS;
/*      */   @NotNull
/*      */   public static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
/*      */   @NotNull
/*      */   public static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
/*      */   @NotNull
/*      */   public static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
/*      */   @NotNull
/*      */   public static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
/*      */   @NotNull
/*      */   public static final String QUERY_ENCODE_SET = " \"'<>#";
/*      */   @NotNull
/*      */   public static final String QUERY_COMPONENT_REENCODE_SET = " \"'<>#&=";
/*      */   @NotNull
/*      */   public static final String QUERY_COMPONENT_ENCODE_SET = " !\"#$&'(),/:;<=>?@[]\\^`{|}~";
/*      */   @NotNull
/*      */   public static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
/*      */   @NotNull
/*      */   public static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
/*      */   @NotNull
/*      */   public static final String FRAGMENT_ENCODE_SET = "";
/*      */   @NotNull
/*      */   public static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
/*      */   
/*      */   @JvmName(name = "scheme")
/*      */   @NotNull
/*      */   public final String scheme() {
/*      */     return this.scheme;
/*      */   }
/*      */   
/*      */   @JvmName(name = "username")
/*      */   @NotNull
/*      */   public final String username() {
/*      */     return this.username;
/*      */   }
/*      */   
/*      */   @JvmName(name = "password")
/*      */   @NotNull
/*      */   public final String password() {
/*      */     return this.password;
/*      */   }
/*      */   
/*      */   @JvmName(name = "host")
/*      */   @NotNull
/*      */   public final String host() {
/*      */     return this.host;
/*      */   }
/*      */   
/*      */   @JvmName(name = "port")
/*      */   public final int port() {
/*      */     return this.port;
/*      */   }
/*      */   
/*      */   @JvmName(name = "pathSegments")
/*      */   @NotNull
/*      */   public final List<String> pathSegments() {
/*      */     return this.pathSegments;
/*      */   }
/*      */   
/*      */   public HttpUrl(@NotNull String scheme, @NotNull String username, @NotNull String password, @NotNull String host, int port, @NotNull List<String> pathSegments, @Nullable List<String> queryNamesAndValues, @Nullable String fragment, @NotNull String url) {
/*  288 */     this.scheme = scheme; this.username = username; this.password = password; this.host = host; this.port = port; this.pathSegments = pathSegments; this.queryNamesAndValues = queryNamesAndValues; this.fragment = fragment; this.url = url;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  384 */     this.isHttps = Intrinsics.areEqual(this.scheme, "https"); } @JvmName(name = "fragment") @Nullable public final String fragment() { return this.fragment; } public final boolean isHttps() { return this.isHttps; }
/*      */    @JvmName(name = "url")
/*      */   @NotNull
/*      */   public final URL url() {
/*      */     try {
/*  389 */       return new URL(this.url);
/*  390 */     } catch (MalformedURLException e) {
/*  391 */       throw (Throwable)new RuntimeException((Throwable)e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "uri")
/*      */   @NotNull
/*      */   public final URI uri() {
/*      */     URI uRI;
/*  409 */     String uri = newBuilder().reencodeForUri$okhttp().toString();
/*      */     try {
/*  411 */       uRI = new URI(uri);
/*  412 */     } catch (URISyntaxException e) {
/*      */       URI uRI1;
/*      */       try {
/*  415 */         String str1 = uri; Regex regex = new Regex("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]"); String str2 = ""; boolean bool = false; String stripped = regex.replace(str1, str2);
/*  416 */         uRI1 = URI.create(stripped);
/*  417 */       } catch (Exception e1) {
/*  418 */         throw (Throwable)new RuntimeException((Throwable)e);
/*      */       } 
/*      */       Intrinsics.checkNotNullExpressionValue(uRI1, "try {\n        val stripp…e) // Unexpected!\n      }");
/*      */       uRI = uRI1;
/*      */     } 
/*      */     return uRI;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "encodedUsername")
/*      */   @NotNull
/*      */   public final String encodedUsername() {
/*  435 */     String str1 = this.username; boolean bool1 = false; if ((str1.length() == 0)) return ""; 
/*  436 */     int usernameStart = this.scheme.length() + 3;
/*  437 */     int usernameEnd = Util.delimiterOffset(this.url, ":@", usernameStart, this.url.length());
/*  438 */     String str2 = this.url; boolean bool2 = false; if (str2 == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str2.substring(usernameStart, usernameEnd), "(this as java.lang.Strin…ing(startIndex, endIndex)"); return str2.substring(usernameStart, usernameEnd);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "encodedPassword")
/*      */   @NotNull
/*      */   public final String encodedPassword() {
/*  453 */     String str1 = this.password; boolean bool1 = false; if ((str1.length() == 0)) return ""; 
/*  454 */     int passwordStart = StringsKt.indexOf$default(this.url, ':', this.scheme.length() + 3, false, 4, null) + 1;
/*  455 */     int passwordEnd = StringsKt.indexOf$default(this.url, '@', 0, false, 6, null);
/*  456 */     String str2 = this.url; boolean bool2 = false; if (str2 == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str2.substring(passwordStart, passwordEnd), "(this as java.lang.Strin…ing(startIndex, endIndex)"); return str2.substring(passwordStart, passwordEnd);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "pathSize")
/*      */   public final int pathSize() {
/*  469 */     return this.pathSegments.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "encodedPath")
/*      */   @NotNull
/*      */   public final String encodedPath() {
/*  483 */     int pathStart = StringsKt.indexOf$default(this.url, '/', this.scheme.length() + 3, false, 4, null);
/*  484 */     int pathEnd = Util.delimiterOffset(this.url, "?#", pathStart, this.url.length());
/*  485 */     String str = this.url; boolean bool = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.substring(pathStart, pathEnd), "(this as java.lang.Strin…ing(startIndex, endIndex)"); return str.substring(pathStart, pathEnd);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "encodedPathSegments")
/*      */   @NotNull
/*      */   public final List<String> encodedPathSegments() {
/*  500 */     int pathStart = StringsKt.indexOf$default(this.url, '/', this.scheme.length() + 3, false, 4, null);
/*  501 */     int pathEnd = Util.delimiterOffset(this.url, "?#", pathStart, this.url.length());
/*  502 */     boolean bool = false; List<String> result = new ArrayList();
/*  503 */     int i = pathStart;
/*  504 */     while (i < pathEnd) {
/*  505 */       i++;
/*  506 */       int segmentEnd = Util.delimiterOffset(this.url, '/', i, pathEnd);
/*  507 */       String str = this.url; boolean bool1 = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.substring(i, segmentEnd), "(this as java.lang.Strin…ing(startIndex, endIndex)"); result.add(str.substring(i, segmentEnd));
/*  508 */       i = segmentEnd;
/*      */     } 
/*  510 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "encodedQuery")
/*      */   @Nullable
/*      */   public final String encodedQuery() {
/*  527 */     if (this.queryNamesAndValues == null) return null; 
/*  528 */     int queryStart = StringsKt.indexOf$default(this.url, '?', 0, false, 6, null) + 1;
/*  529 */     int queryEnd = Util.delimiterOffset(this.url, '#', queryStart, this.url.length());
/*  530 */     String str = this.url; boolean bool = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.substring(queryStart, queryEnd), "(this as java.lang.Strin…ing(startIndex, endIndex)"); return str.substring(queryStart, queryEnd);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "query")
/*      */   @Nullable
/*      */   public final String query() {
/*  548 */     if (this.queryNamesAndValues == null) return null; 
/*  549 */     StringBuilder result = new StringBuilder();
/*  550 */     Companion.toQueryString$okhttp(this.queryNamesAndValues, result);
/*  551 */     return result.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "querySize")
/*      */   public final int querySize() {
/*  569 */     return (this.queryNamesAndValues != null) ? (this.queryNamesAndValues.size() / 2) : 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public final String queryParameter(@NotNull String name) {
/*  585 */     Intrinsics.checkNotNullParameter(name, "name"); if (this.queryNamesAndValues == null) return null; 
/*  586 */     int i = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2).getFirst(), j = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2).getLast(), k = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2).getStep(); if ((k >= 0) ? (i <= j) : (i >= j))
/*  587 */       while (true) { if (Intrinsics.areEqual(name, this.queryNamesAndValues.get(i)))
/*  588 */           return this.queryNamesAndValues.get(i + 1);  if (i != j) {
/*      */           int m = i + k; continue;
/*      */         }  break; }
/*  591 */         return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "queryParameterNames")
/*      */   @NotNull
/*      */   public final Set<String> queryParameterNames() {
/*  608 */     if (this.queryNamesAndValues == null) return SetsKt.emptySet(); 
/*  609 */     LinkedHashSet<String> result = new LinkedHashSet();
/*  610 */     int i = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2).getFirst(), j = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2).getLast(), k = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2).getStep(); if ((k >= 0) ? (i <= j) : (i >= j))
/*  611 */       while (true) { Intrinsics.checkNotNull(this.queryNamesAndValues.get(i)); result.add(this.queryNamesAndValues.get(i)); if (i != j) { int m = i + k; continue; }
/*      */          break; }
/*  613 */         Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableSet(result), "Collections.unmodifiableSet(result)"); return Collections.unmodifiableSet(result);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public final List<String> queryParameterValues(@NotNull String name) {
/*  630 */     Intrinsics.checkNotNullParameter(name, "name"); if (this.queryNamesAndValues == null) return CollectionsKt.emptyList(); 
/*  631 */     int i = 0; List<?> result = new ArrayList();
/*  632 */     i = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2).getFirst(); int j = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2).getLast(), k = RangesKt.step((IntProgression)RangesKt.until(0, this.queryNamesAndValues.size()), 2).getStep(); if ((k >= 0) ? (i <= j) : (i >= j))
/*  633 */       while (true) { if (Intrinsics.areEqual(name, this.queryNamesAndValues.get(i)))
/*  634 */           result.add(this.queryNamesAndValues.get(i + 1));  if (i != j) {
/*      */           int m = i + k; continue;
/*      */         }  break; }
/*  637 */         Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableList(result), "Collections.unmodifiableList(result)"); return (List)Collections.unmodifiableList(result);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public final String queryParameterName(int index) {
/*  654 */     if (this.queryNamesAndValues == null) throw (Throwable)new IndexOutOfBoundsException(); 
/*  655 */     Intrinsics.checkNotNull(this.queryNamesAndValues.get(index * 2)); return this.queryNamesAndValues.get(index * 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public final String queryParameterValue(int index) {
/*  672 */     if (this.queryNamesAndValues == null) throw (Throwable)new IndexOutOfBoundsException(); 
/*  673 */     return this.queryNamesAndValues.get(index * 2 + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @JvmName(name = "encodedFragment")
/*      */   @Nullable
/*      */   public final String encodedFragment() {
/*  689 */     if (this.fragment == null) return null; 
/*  690 */     int fragmentStart = StringsKt.indexOf$default(this.url, '#', 0, false, 6, null) + 1;
/*  691 */     String str = this.url; boolean bool = false; if (str == null) throw new NullPointerException("null cannot be cast to non-null type java.lang.String");  Intrinsics.checkNotNullExpressionValue(str.substring(fragmentStart), "(this as java.lang.String).substring(startIndex)"); return str.substring(fragmentStart);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public final String redact() {
/*  700 */     Intrinsics.checkNotNull(newBuilder("/...")); return newBuilder("/...")
/*  701 */       .username("")
/*  702 */       .password("")
/*  703 */       .build()
/*  704 */       .toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public final HttpUrl resolve(@NotNull String link) {
/*  711 */     Intrinsics.checkNotNullParameter(link, "link"); newBuilder(link); return (newBuilder(link) != null) ? newBuilder(link).build() : null;
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public final Builder newBuilder() {
/*  717 */     Builder result = new Builder();
/*  718 */     result.setScheme$okhttp(this.scheme);
/*  719 */     result.setEncodedUsername$okhttp(encodedUsername());
/*  720 */     result.setEncodedPassword$okhttp(encodedPassword());
/*  721 */     result.setHost$okhttp(this.host);
/*      */     
/*  723 */     result.setPort$okhttp((this.port != Companion.defaultPort(this.scheme)) ? this.port : -1);
/*  724 */     result.getEncodedPathSegments$okhttp().clear();
/*  725 */     result.getEncodedPathSegments$okhttp().addAll(encodedPathSegments());
/*  726 */     result.encodedQuery(encodedQuery());
/*  727 */     result.setEncodedFragment$okhttp(encodedFragment());
/*  728 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public final Builder newBuilder(@NotNull String link) {
/*      */     Builder builder;
/*  736 */     Intrinsics.checkNotNullParameter(link, "link"); try {
/*  737 */       builder = (new Builder()).parse$okhttp(this, link);
/*  738 */     } catch (IllegalArgumentException _) {
/*  739 */       builder = null;
/*      */     } 
/*      */     return builder;
/*      */   }
/*      */   public boolean equals(@Nullable Object other) {
/*  744 */     return (other instanceof HttpUrl && Intrinsics.areEqual(((HttpUrl)other).url, this.url));
/*      */   }
/*      */   
/*  747 */   public int hashCode() { return this.url.hashCode(); } @NotNull
/*      */   public String toString() {
/*  749 */     return this.url;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public final String topPrivateDomain() {
/*  769 */     return Util.canParseAsIpAddress(this.host) ? 
/*  770 */       null : 
/*      */       
/*  772 */       PublicSuffixDatabase.Companion.get().getEffectiveTldPlusOne(this.host);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to toUrl()", replaceWith = @ReplaceWith(imports = {}, expression = "toUrl()"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_url")
/*      */   @NotNull
/*      */   public final URL -deprecated_url() {
/*  781 */     return url();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to toUri()", replaceWith = @ReplaceWith(imports = {}, expression = "toUri()"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_uri")
/*      */   @NotNull
/*      */   public final URI -deprecated_uri() {
/*  788 */     return uri();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "scheme"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_scheme")
/*      */   @NotNull
/*      */   public final String -deprecated_scheme() {
/*  795 */     return this.scheme;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedUsername"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_encodedUsername")
/*      */   @NotNull
/*      */   public final String -deprecated_encodedUsername() {
/*  802 */     return encodedUsername();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "username"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_username")
/*      */   @NotNull
/*      */   public final String -deprecated_username() {
/*  809 */     return this.username;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedPassword"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_encodedPassword")
/*      */   @NotNull
/*      */   public final String -deprecated_encodedPassword() {
/*  816 */     return encodedPassword();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "password"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_password")
/*      */   @NotNull
/*      */   public final String -deprecated_password() {
/*  823 */     return this.password;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "host"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_host")
/*      */   @NotNull
/*      */   public final String -deprecated_host() {
/*  830 */     return this.host;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "port"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_port")
/*      */   public final int -deprecated_port() {
/*  837 */     return this.port;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "pathSize"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_pathSize")
/*      */   public final int -deprecated_pathSize() {
/*  844 */     return pathSize();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedPath"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_encodedPath")
/*      */   @NotNull
/*      */   public final String -deprecated_encodedPath() {
/*  851 */     return encodedPath();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedPathSegments"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_encodedPathSegments")
/*      */   @NotNull
/*      */   public final List<String> -deprecated_encodedPathSegments() {
/*  858 */     return encodedPathSegments();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "pathSegments"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_pathSegments")
/*      */   @NotNull
/*      */   public final List<String> -deprecated_pathSegments() {
/*  865 */     return this.pathSegments;
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedQuery"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_encodedQuery")
/*      */   @Nullable
/*      */   public final String -deprecated_encodedQuery() {
/*  872 */     return encodedQuery();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "query"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_query")
/*      */   @Nullable
/*      */   public final String -deprecated_query() {
/*  879 */     return query();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "querySize"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_querySize")
/*      */   public final int -deprecated_querySize() {
/*  886 */     return querySize();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "queryParameterNames"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_queryParameterNames")
/*      */   @NotNull
/*      */   public final Set<String> -deprecated_queryParameterNames() {
/*  893 */     return queryParameterNames();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "encodedFragment"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_encodedFragment")
/*      */   @Nullable
/*      */   public final String -deprecated_encodedFragment() {
/*  900 */     return encodedFragment();
/*      */   }
/*      */   
/*      */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "fragment"), level = DeprecationLevel.ERROR)
/*      */   @JvmName(name = "-deprecated_fragment")
/*      */   @Nullable
/*      */   public final String -deprecated_fragment() {
/*  907 */     return this.fragment; } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000<\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\b\n\002\020!\n\002\b\r\n\002\020\b\n\002\b\022\n\002\020\013\n\002\b\004\n\002\030\002\n\002\b\f\n\002\020\002\n\002\b\027\030\000 V2\0020\001:\001VB\005¢\006\002\020\002J\016\020#\032\0020\0002\006\020$\032\0020\004J\016\020%\032\0020\0002\006\020\f\032\0020\004J\030\020&\032\0020\0002\006\020'\032\0020\0042\b\020(\032\004\030\0010\004J\016\020)\032\0020\0002\006\020*\032\0020\004J\016\020+\032\0020\0002\006\020,\032\0020\004J\030\020+\032\0020\0002\006\020,\032\0020\0042\006\020-\032\0020.H\002J\030\020/\032\0020\0002\006\0200\032\0020\0042\b\0201\032\004\030\0010\004J\006\0202\032\00203J\b\0204\032\0020\033H\002J\020\020\003\032\0020\0002\b\020\003\032\004\030\0010\004J\016\020\t\032\0020\0002\006\020\t\032\0020\004J\016\0205\032\0020\0002\006\0205\032\0020\004J\020\0206\032\0020\0002\b\0206\032\004\030\0010\004J\016\020\024\032\0020\0002\006\020\024\032\0020\004J\020\0207\032\0020\0002\b\0207\032\004\030\0010\004J\016\020\027\032\0020\0002\006\020\027\032\0020\004J\020\0208\032\0020.2\006\0209\032\0020\004H\002J\020\020:\032\0020.2\006\0209\032\0020\004H\002J\037\020;\032\0020\0002\b\020<\032\004\030\001032\006\0209\032\0020\004H\000¢\006\002\b=J\016\020>\032\0020\0002\006\020>\032\0020\004J\b\020?\032\0020@H\002J\016\020\032\032\0020\0002\006\020\032\032\0020\033J0\020A\032\0020@2\006\0209\032\0020\0042\006\020B\032\0020\0332\006\020C\032\0020\0332\006\020D\032\0020.2\006\020-\032\0020.H\002J\020\020E\032\0020\0002\b\020E\032\004\030\0010\004J\r\020F\032\0020\000H\000¢\006\002\bGJ\020\020H\032\0020@2\006\020I\032\0020\004H\002J\016\020J\032\0020\0002\006\020'\032\0020\004J\016\020K\032\0020\0002\006\0200\032\0020\004J\016\020L\032\0020\0002\006\020M\032\0020\033J \020N\032\0020@2\006\0209\032\0020\0042\006\020O\032\0020\0332\006\020C\032\0020\033H\002J\016\020 \032\0020\0002\006\020 \032\0020\004J\026\020P\032\0020\0002\006\020M\032\0020\0332\006\020$\032\0020\004J\030\020Q\032\0020\0002\006\020'\032\0020\0042\b\020(\032\004\030\0010\004J\026\020R\032\0020\0002\006\020M\032\0020\0332\006\020*\032\0020\004J\030\020S\032\0020\0002\006\0200\032\0020\0042\b\0201\032\004\030\0010\004J\b\020T\032\0020\004H\026J\016\020U\032\0020\0002\006\020U\032\0020\004R\034\020\003\032\004\030\0010\004X\016¢\006\016\n\000\032\004\b\005\020\006\"\004\b\007\020\bR\032\020\t\032\0020\004X\016¢\006\016\n\000\032\004\b\n\020\006\"\004\b\013\020\bR\032\020\f\032\b\022\004\022\0020\0040\rX\004¢\006\b\n\000\032\004\b\016\020\017R$\020\020\032\f\022\006\022\004\030\0010\004\030\0010\rX\016¢\006\016\n\000\032\004\b\021\020\017\"\004\b\022\020\023R\032\020\024\032\0020\004X\016¢\006\016\n\000\032\004\b\025\020\006\"\004\b\026\020\bR\034\020\027\032\004\030\0010\004X\016¢\006\016\n\000\032\004\b\030\020\006\"\004\b\031\020\bR\032\020\032\032\0020\033X\016¢\006\016\n\000\032\004\b\034\020\035\"\004\b\036\020\037R\034\020 \032\004\030\0010\004X\016¢\006\016\n\000\032\004\b!\020\006\"\004\b\"\020\b¨\006W"}, d2 = {"Lokhttp3/HttpUrl$Builder;", "", "()V", "encodedFragment", "", "getEncodedFragment$okhttp", "()Ljava/lang/String;", "setEncodedFragment$okhttp", "(Ljava/lang/String;)V", "encodedPassword", "getEncodedPassword$okhttp", "setEncodedPassword$okhttp", "encodedPathSegments", "", "getEncodedPathSegments$okhttp", "()Ljava/util/List;", "encodedQueryNamesAndValues", "getEncodedQueryNamesAndValues$okhttp", "setEncodedQueryNamesAndValues$okhttp", "(Ljava/util/List;)V", "encodedUsername", "getEncodedUsername$okhttp", "setEncodedUsername$okhttp", "host", "getHost$okhttp", "setHost$okhttp", "port", "", "getPort$okhttp", "()I", "setPort$okhttp", "(I)V", "scheme", "getScheme$okhttp", "setScheme$okhttp", "addEncodedPathSegment", "encodedPathSegment", "addEncodedPathSegments", "addEncodedQueryParameter", "encodedName", "encodedValue", "addPathSegment", "pathSegment", "addPathSegments", "pathSegments", "alreadyEncoded", "", "addQueryParameter", "name", "value", "build", "Lokhttp3/HttpUrl;", "effectivePort", "encodedPath", "encodedQuery", "fragment", "isDot", "input", "isDotDot", "parse", "base", "parse$okhttp", "password", "pop", "", "push", "pos", "limit", "addTrailingSlash", "query", "reencodeForUri", "reencodeForUri$okhttp", "removeAllCanonicalQueryParameters", "canonicalName", "removeAllEncodedQueryParameters", "removeAllQueryParameters", "removePathSegment", "index", "resolvePath", "startPos", "setEncodedPathSegment", "setEncodedQueryParameter", "setPathSegment", "setQueryParameter", "toString", "username", "Companion", "okhttp"})
/*      */   public static final class Builder { @Nullable
/*      */     private String scheme; @Nullable
/*  910 */     public final String getScheme$okhttp() { return this.scheme; } public final void setScheme$okhttp(@Nullable String <set-?>) { this.scheme = <set-?>; } @NotNull
/*  911 */     private String encodedUsername = ""; @NotNull public final String getEncodedUsername$okhttp() { return this.encodedUsername; } public final void setEncodedUsername$okhttp(@NotNull String <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.encodedUsername = <set-?>; } @NotNull
/*  912 */     private String encodedPassword = ""; @Nullable private String host; @NotNull public final String getEncodedPassword$okhttp() { return this.encodedPassword; } public final void setEncodedPassword$okhttp(@NotNull String <set-?>) { Intrinsics.checkNotNullParameter(<set-?>, "<set-?>"); this.encodedPassword = <set-?>; } @Nullable
/*  913 */     public final String getHost$okhttp() { return this.host; } public final void setHost$okhttp(@Nullable String <set-?>) { this.host = <set-?>; }
/*  914 */      private int port = -1; @NotNull private final List<String> encodedPathSegments; @Nullable private List<String> encodedQueryNamesAndValues; @Nullable private String encodedFragment; @NotNull public static final String INVALID_HOST = "Invalid URL host"; public static final Companion Companion = new Companion(null); public final int getPort$okhttp() { return this.port; } public final void setPort$okhttp(int <set-?>) { this.port = <set-?>; } @NotNull
/*  915 */     public final List<String> getEncodedPathSegments$okhttp() { return this.encodedPathSegments; } public Builder() { boolean bool = false; this.encodedPathSegments = new ArrayList<>();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  920 */       this.encodedPathSegments.add(""); }
/*      */     @Nullable
/*      */     public final List<String> getEncodedQueryNamesAndValues$okhttp() { return this.encodedQueryNamesAndValues; }
/*      */     public final void setEncodedQueryNamesAndValues$okhttp(@Nullable List<String> <set-?>) {
/*      */       this.encodedQueryNamesAndValues = <set-?>;
/*      */     } @NotNull
/*  926 */     public final Builder scheme(@NotNull String scheme) { Intrinsics.checkNotNullParameter(scheme, "scheme"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$scheme$1 = 0;
/*      */       
/*  928 */       if (StringsKt.equals(scheme, "http", true)) { $this$apply.scheme = "http"; }
/*  929 */       else if (StringsKt.equals(scheme, "https", true)) { $this$apply.scheme = "https"; }
/*  930 */       else { throw (Throwable)new IllegalArgumentException("unexpected scheme: " + scheme); }
/*      */       
/*      */       return builder1; }
/*      */     @Nullable public final String getEncodedFragment$okhttp() { return this.encodedFragment; }
/*  934 */     public final void setEncodedFragment$okhttp(@Nullable String <set-?>) { this.encodedFragment = <set-?>; } @NotNull public final Builder username(@NotNull String username) { Intrinsics.checkNotNullParameter(username, "username"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$username$1 = 0;
/*  935 */       $this$apply.encodedUsername = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, username, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
/*      */       return builder1; } @NotNull
/*      */     public final Builder encodedUsername(@NotNull String encodedUsername) {
/*  938 */       Intrinsics.checkNotNullParameter(encodedUsername, "encodedUsername"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$encodedUsername$1 = 0;
/*  939 */       $this$apply.encodedUsername = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedUsername, 0, 0, 
/*  940 */           " \"':;<=>@[]^`{}|/\\?#", 
/*  941 */           true, false, false, false, null, 243, null);
/*      */       return builder1;
/*      */     }
/*      */     @NotNull
/*  945 */     public final Builder password(@NotNull String password) { Intrinsics.checkNotNullParameter(password, "password"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$password$1 = 0;
/*  946 */       $this$apply.encodedPassword = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, password, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
/*      */       return builder1; } @NotNull
/*      */     public final Builder encodedPassword(@NotNull String encodedPassword) {
/*  949 */       Intrinsics.checkNotNullParameter(encodedPassword, "encodedPassword"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$encodedPassword$1 = 0;
/*  950 */       $this$apply.encodedPassword = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedPassword, 0, 0, 
/*  951 */           " \"':;<=>@[]^`{}|/\\?#", 
/*  952 */           true, false, false, false, null, 243, null);
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder host(@NotNull String host) {
/*  960 */       Intrinsics.checkNotNullParameter(host, "host"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$host$1 = 0;
/*  961 */       if (HostnamesKt.toCanonicalHost(HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, host, 0, 0, false, 7, null)) != null) { String encoded = HostnamesKt.toCanonicalHost(HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, host, 0, 0, false, 7, null));
/*      */         
/*  963 */         $this$apply.host = encoded;
/*      */         return builder1; }
/*      */       
/*      */       HostnamesKt.toCanonicalHost(HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, host, 0, 0, false, 7, null));
/*      */       throw (Throwable)new IllegalArgumentException("unexpected host: " + host);
/*      */     } @NotNull
/*      */     public final Builder port(int port) {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: astore_2
/*      */       //   2: iconst_0
/*      */       //   3: istore_3
/*      */       //   4: iconst_0
/*      */       //   5: istore #4
/*      */       //   7: aload_2
/*      */       //   8: checkcast okhttp3/HttpUrl$Builder
/*      */       //   11: astore #5
/*      */       //   13: iconst_0
/*      */       //   14: istore #6
/*      */       //   16: ldc 65535
/*      */       //   18: iconst_1
/*      */       //   19: iload_1
/*      */       //   20: istore #7
/*      */       //   22: iload #7
/*      */       //   24: if_icmple -> 31
/*      */       //   27: pop
/*      */       //   28: goto -> 40
/*      */       //   31: iload #7
/*      */       //   33: if_icmplt -> 40
/*      */       //   36: iconst_1
/*      */       //   37: goto -> 41
/*      */       //   40: iconst_0
/*      */       //   41: istore #7
/*      */       //   43: iconst_0
/*      */       //   44: istore #8
/*      */       //   46: iconst_0
/*      */       //   47: istore #9
/*      */       //   49: iload #7
/*      */       //   51: ifne -> 94
/*      */       //   54: iconst_0
/*      */       //   55: istore #10
/*      */       //   57: new java/lang/StringBuilder
/*      */       //   60: dup
/*      */       //   61: invokespecial <init> : ()V
/*      */       //   64: ldc 'unexpected port: '
/*      */       //   66: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   69: iload_1
/*      */       //   70: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*      */       //   73: invokevirtual toString : ()Ljava/lang/String;
/*      */       //   76: astore #9
/*      */       //   78: new java/lang/IllegalArgumentException
/*      */       //   81: dup
/*      */       //   82: aload #9
/*      */       //   84: invokevirtual toString : ()Ljava/lang/String;
/*      */       //   87: invokespecial <init> : (Ljava/lang/String;)V
/*      */       //   90: checkcast java/lang/Throwable
/*      */       //   93: athrow
/*      */       //   94: aload #5
/*      */       //   96: iload_1
/*      */       //   97: putfield port : I
/*      */       //   100: nop
/*      */       //   101: aload_2
/*      */       //   102: checkcast okhttp3/HttpUrl$Builder
/*      */       //   105: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #966	-> 0
/*      */       //   #967	-> 16
/*      */       //   #1869	-> 54
/*      */       //   #967	-> 57
/*      */       //   #967	-> 76
/*      */       //   #968	-> 94
/*      */       //   #969	-> 100
/*      */       //   #966	-> 101
/*      */       //   #969	-> 105
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   57	19	10	$i$a$-require-HttpUrl$Builder$port$1$1	I
/*      */       //   13	88	5	$this$apply	Lokhttp3/HttpUrl$Builder;
/*      */       //   16	85	6	$i$a$-apply-HttpUrl$Builder$port$1	I
/*      */       //   0	106	0	this	Lokhttp3/HttpUrl$Builder;
/*      */       //   0	106	1	port	I
/*      */     } private final int effectivePort() {
/*  972 */       Intrinsics.checkNotNull(this.scheme); return (this.port != -1) ? this.port : HttpUrl.Companion.defaultPort(this.scheme);
/*      */     } @NotNull
/*      */     public final Builder addPathSegment(@NotNull String pathSegment) {
/*  975 */       Intrinsics.checkNotNullParameter(pathSegment, "pathSegment"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$addPathSegment$1 = 0;
/*  976 */       $this$apply.push(pathSegment, 0, pathSegment.length(), false, false);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder addPathSegments(@NotNull String pathSegments)
/*      */     {
/*  983 */       Intrinsics.checkNotNullParameter(pathSegments, "pathSegments"); return addPathSegments(pathSegments, false); } @NotNull
/*      */     public final Builder addEncodedPathSegment(@NotNull String encodedPathSegment) {
/*  985 */       Intrinsics.checkNotNullParameter(encodedPathSegment, "encodedPathSegment"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$addEncodedPathSegment$1 = 0;
/*  986 */       $this$apply.push(encodedPathSegment, 0, encodedPathSegment.length(), false, 
/*  987 */           true);
/*      */       return builder1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder addEncodedPathSegments(@NotNull String encodedPathSegments) {
/*  995 */       Intrinsics.checkNotNullParameter(encodedPathSegments, "encodedPathSegments"); return addPathSegments(encodedPathSegments, true);
/*      */     }
/*  997 */     private final Builder addPathSegments(String pathSegments, boolean alreadyEncoded) { Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$addPathSegments$1 = 0;
/*  998 */       int offset = 0;
/*      */       while (true) {
/* 1000 */         int segmentEnd = Util.delimiterOffset(pathSegments, "/\\", offset, pathSegments.length());
/* 1001 */         boolean addTrailingSlash = (segmentEnd < pathSegments.length());
/* 1002 */         $this$apply.push(pathSegments, offset, segmentEnd, addTrailingSlash, alreadyEncoded);
/* 1003 */         offset = segmentEnd + 1;
/* 1004 */         if (offset > pathSegments.length())
/*      */           return builder1; 
/*      */       }  } @NotNull
/* 1007 */     public final Builder setPathSegment(int index, @NotNull String pathSegment) { Intrinsics.checkNotNullParameter(pathSegment, "pathSegment"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$setPathSegment$1 = 0;
/* 1008 */       String canonicalPathSegment = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, pathSegment, 0, 0, " \"<>^`{}|/\\?#", false, false, false, false, null, 251, null);
/* 1009 */       boolean bool3 = (!$this$apply.isDot(canonicalPathSegment) && !$this$apply.isDotDot(canonicalPathSegment)) ? true : false, bool4 = false, bool5 = false; if (!bool3) { int $i$a$-require-HttpUrl$Builder$setPathSegment$1$1 = 0; String str = 
/* 1010 */           "unexpected path segment: " + pathSegment; throw (Throwable)new IllegalArgumentException(str.toString()); }
/*      */       
/* 1012 */       $this$apply.encodedPathSegments.set(index, canonicalPathSegment);
/*      */       return builder1; }
/*      */     @NotNull
/* 1015 */     public final Builder setEncodedPathSegment(int index, @NotNull String encodedPathSegment) { Intrinsics.checkNotNullParameter(encodedPathSegment, "encodedPathSegment"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$setEncodedPathSegment$1 = 0;
/* 1016 */       String canonicalPathSegment = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedPathSegment, 0, 0, 
/* 1017 */           " \"<>^`{}|/\\?#", 
/* 1018 */           true, false, false, false, null, 243, null);
/*      */       
/* 1020 */       $this$apply.encodedPathSegments.set(index, canonicalPathSegment);
/* 1021 */       boolean bool3 = (!$this$apply.isDot(canonicalPathSegment) && !$this$apply.isDotDot(canonicalPathSegment)) ? true : false, bool4 = false, bool5 = false; if (!bool3) { int $i$a$-require-HttpUrl$Builder$setEncodedPathSegment$1$1 = 0; String str = 
/* 1022 */           "unexpected path segment: " + encodedPathSegment;
/*      */         throw (Throwable)new IllegalArgumentException(str.toString()); }
/*      */       
/*      */       return builder1; } @NotNull
/* 1026 */     public final Builder removePathSegment(int index) { Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$removePathSegment$1 = 0;
/* 1027 */       $this$apply.encodedPathSegments.remove(index);
/* 1028 */       if ($this$apply.encodedPathSegments.isEmpty())
/* 1029 */         $this$apply.encodedPathSegments.add(""); 
/*      */       return builder1; }
/*      */     
/*      */     @NotNull
/* 1033 */     public final Builder encodedPath(@NotNull String encodedPath) { Intrinsics.checkNotNullParameter(encodedPath, "encodedPath"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-HttpUrl$Builder$encodedPath$1 = 0;
/* 1034 */       boolean bool = StringsKt.startsWith$default(encodedPath, "/", false, 2, null); boolean bool3 = false, bool4 = false; if (!bool) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1869 */         int $i$a$-require-HttpUrl$Builder$encodedPath$1$1 = 0; String str = "unexpected encodedPath: " + encodedPath; throw (Throwable)new IllegalArgumentException(str.toString());
/* 1870 */       }  $this$apply.resolvePath(encodedPath, 0, encodedPath.length()); return builder1; } @NotNull public final HttpUrl build() { if (this.scheme != null) { if (this.host != null) { List<String> list1 = this.encodedPathSegments; int i = effectivePort(); String str4 = this.host, str3 = HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedPassword, 0, 0, false, 7, null), str2 = HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedUsername, 0, 0, false, 7, null), str1 = this.scheme; int $i$f$map = 0; List<String> list2 = list1; Collection<String> collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10)); int $i$f$mapTo = 0;
/* 1871 */           for (String item$iv$iv : list2) {
/* 1872 */             String str12 = item$iv$iv; Collection<String> collection1 = collection; int $i$a$-map-HttpUrl$Builder$build$1 = 0; String str13 = HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, str12, 0, 0, false, 7, null); collection1.add(str13);
/* 1873 */           }  List list3 = (List)collection; Iterable<String> $this$map$iv = this.encodedQueryNamesAndValues; list3 = list3; i = i; str4 = str4; str3 = str3; str2 = str2; str1 = str1; $i$f$map = 0;
/* 1874 */           Iterable<String> $this$mapTo$iv$iv = $this$map$iv; Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)); $i$f$mapTo = 0;
/* 1875 */           for (String item$iv$iv : $this$mapTo$iv$iv) {
/* 1876 */             String it = item$iv$iv; Collection collection1 = destination$iv$iv; int $i$a$-map-HttpUrl$Builder$build$2 = 0;
/* 1877 */           }  List list4 = (List)destination$iv$iv;
/*      */           String str5 = toString(), str6 = (this.encodedFragment != null) ? HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedFragment, 0, 0, false, 7, null) : null;
/*      */           List<String> list5 = (this.encodedQueryNamesAndValues != null) ? list4 : null;
/*      */           int j = i;
/*      */           String str7 = str4, str8 = str3, str9 = str2, str10 = str1, str11 = (it != null) ? HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, it, 0, 0, true, 3, null) : null;
/*      */           return new HttpUrl(str11, str10, str9, str8, str7, j, list5, str6, str5); }
/*      */         
/*      */         throw (Throwable)new IllegalStateException("host == null"); }
/*      */       
/*      */       throw (Throwable)new IllegalStateException("scheme == null"); }
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public final Builder query(@Nullable String query) {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: astore_2
/*      */       //   2: iconst_0
/*      */       //   3: istore_3
/*      */       //   4: iconst_0
/*      */       //   5: istore #4
/*      */       //   7: aload_2
/*      */       //   8: checkcast okhttp3/HttpUrl$Builder
/*      */       //   11: astore #5
/*      */       //   13: iconst_0
/*      */       //   14: istore #6
/*      */       //   16: aload #5
/*      */       //   18: aload_1
/*      */       //   19: dup
/*      */       //   20: ifnull -> 58
/*      */       //   23: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
/*      */       //   26: swap
/*      */       //   27: iconst_0
/*      */       //   28: iconst_0
/*      */       //   29: ldc_w ' "'<>#'
/*      */       //   32: iconst_0
/*      */       //   33: iconst_0
/*      */       //   34: iconst_1
/*      */       //   35: iconst_0
/*      */       //   36: aconst_null
/*      */       //   37: sipush #219
/*      */       //   40: aconst_null
/*      */       //   41: invokestatic canonicalize$okhttp$default : (Lokhttp3/HttpUrl$Companion;Ljava/lang/String;IILjava/lang/String;ZZZZLjava/nio/charset/Charset;ILjava/lang/Object;)Ljava/lang/String;
/*      */       //   44: dup
/*      */       //   45: ifnull -> 58
/*      */       //   48: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
/*      */       //   51: swap
/*      */       //   52: invokevirtual toQueryNamesAndValues$okhttp : (Ljava/lang/String;)Ljava/util/List;
/*      */       //   55: goto -> 60
/*      */       //   58: pop
/*      */       //   59: aconst_null
/*      */       //   60: putfield encodedQueryNamesAndValues : Ljava/util/List;
/*      */       //   63: nop
/*      */       //   64: aload_2
/*      */       //   65: checkcast okhttp3/HttpUrl$Builder
/*      */       //   68: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #1038	-> 0
/*      */       //   #1039	-> 16
/*      */       //   #1042	-> 16
/*      */       //   #1040	-> 29
/*      */       //   #1041	-> 34
/*      */       //   #1039	-> 41
/*      */       //   #1042	-> 52
/*      */       //   #1043	-> 63
/*      */       //   #1038	-> 64
/*      */       //   #1043	-> 68
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   13	51	5	$this$apply	Lokhttp3/HttpUrl$Builder;
/*      */       //   16	48	6	$i$a$-apply-HttpUrl$Builder$query$1	I
/*      */       //   0	69	0	this	Lokhttp3/HttpUrl$Builder;
/*      */       //   0	69	1	query	Ljava/lang/String;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder encodedQuery(@Nullable String encodedQuery) {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: astore_2
/*      */       //   2: iconst_0
/*      */       //   3: istore_3
/*      */       //   4: iconst_0
/*      */       //   5: istore #4
/*      */       //   7: aload_2
/*      */       //   8: checkcast okhttp3/HttpUrl$Builder
/*      */       //   11: astore #5
/*      */       //   13: iconst_0
/*      */       //   14: istore #6
/*      */       //   16: aload #5
/*      */       //   18: aload_1
/*      */       //   19: dup
/*      */       //   20: ifnull -> 58
/*      */       //   23: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
/*      */       //   26: swap
/*      */       //   27: iconst_0
/*      */       //   28: iconst_0
/*      */       //   29: ldc_w ' "'<>#'
/*      */       //   32: iconst_1
/*      */       //   33: iconst_0
/*      */       //   34: iconst_1
/*      */       //   35: iconst_0
/*      */       //   36: aconst_null
/*      */       //   37: sipush #211
/*      */       //   40: aconst_null
/*      */       //   41: invokestatic canonicalize$okhttp$default : (Lokhttp3/HttpUrl$Companion;Ljava/lang/String;IILjava/lang/String;ZZZZLjava/nio/charset/Charset;ILjava/lang/Object;)Ljava/lang/String;
/*      */       //   44: dup
/*      */       //   45: ifnull -> 58
/*      */       //   48: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
/*      */       //   51: swap
/*      */       //   52: invokevirtual toQueryNamesAndValues$okhttp : (Ljava/lang/String;)Ljava/util/List;
/*      */       //   55: goto -> 60
/*      */       //   58: pop
/*      */       //   59: aconst_null
/*      */       //   60: putfield encodedQueryNamesAndValues : Ljava/util/List;
/*      */       //   63: nop
/*      */       //   64: aload_2
/*      */       //   65: checkcast okhttp3/HttpUrl$Builder
/*      */       //   68: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #1045	-> 0
/*      */       //   #1046	-> 16
/*      */       //   #1050	-> 16
/*      */       //   #1047	-> 29
/*      */       //   #1048	-> 32
/*      */       //   #1049	-> 34
/*      */       //   #1046	-> 41
/*      */       //   #1050	-> 52
/*      */       //   #1051	-> 63
/*      */       //   #1045	-> 64
/*      */       //   #1051	-> 68
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   13	51	5	$this$apply	Lokhttp3/HttpUrl$Builder;
/*      */       //   16	48	6	$i$a$-apply-HttpUrl$Builder$encodedQuery$1	I
/*      */       //   0	69	0	this	Lokhttp3/HttpUrl$Builder;
/*      */       //   0	69	1	encodedQuery	Ljava/lang/String;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder addQueryParameter(@NotNull String name, @Nullable String value) {
/*      */       Intrinsics.checkNotNullParameter(name, "name");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-HttpUrl$Builder$addQueryParameter$1 = 0;
/*      */       if ($this$apply.encodedQueryNamesAndValues == null) {
/*      */         boolean bool = false;
/*      */         $this$apply.encodedQueryNamesAndValues = new ArrayList<>();
/*      */       } 
/*      */       Intrinsics.checkNotNull($this$apply.encodedQueryNamesAndValues);
/*      */       $this$apply.encodedQueryNamesAndValues.add(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219, null));
/*      */       Intrinsics.checkNotNull($this$apply.encodedQueryNamesAndValues);
/*      */       $this$apply.encodedQueryNamesAndValues.add((value != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, value, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219, null) : null);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder addEncodedQueryParameter(@NotNull String encodedName, @Nullable String encodedValue) {
/*      */       Intrinsics.checkNotNullParameter(encodedName, "encodedName");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-HttpUrl$Builder$addEncodedQueryParameter$1 = 0;
/*      */       if ($this$apply.encodedQueryNamesAndValues == null) {
/*      */         boolean bool = false;
/*      */         $this$apply.encodedQueryNamesAndValues = new ArrayList<>();
/*      */       } 
/*      */       Intrinsics.checkNotNull($this$apply.encodedQueryNamesAndValues);
/*      */       $this$apply.encodedQueryNamesAndValues.add(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedName, 0, 0, " \"'<>#&=", true, false, true, false, null, 211, null));
/*      */       Intrinsics.checkNotNull($this$apply.encodedQueryNamesAndValues);
/*      */       $this$apply.encodedQueryNamesAndValues.add((encodedValue != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedValue, 0, 0, " \"'<>#&=", true, false, true, false, null, 211, null) : null);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder setQueryParameter(@NotNull String name, @Nullable String value) {
/*      */       Intrinsics.checkNotNullParameter(name, "name");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-HttpUrl$Builder$setQueryParameter$1 = 0;
/*      */       $this$apply.removeAllQueryParameters(name);
/*      */       $this$apply.addQueryParameter(name, value);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder setEncodedQueryParameter(@NotNull String encodedName, @Nullable String encodedValue) {
/*      */       Intrinsics.checkNotNullParameter(encodedName, "encodedName");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-HttpUrl$Builder$setEncodedQueryParameter$1 = 0;
/*      */       $this$apply.removeAllEncodedQueryParameters(encodedName);
/*      */       $this$apply.addEncodedQueryParameter(encodedName, encodedValue);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder removeAllQueryParameters(@NotNull String name) {
/*      */       Intrinsics.checkNotNullParameter(name, "name");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-HttpUrl$Builder$removeAllQueryParameters$1 = 0;
/*      */       if ($this$apply.encodedQueryNamesAndValues == null)
/*      */         return $this$apply; 
/*      */       String nameToRemove = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, name, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219, null);
/*      */       $this$apply.removeAllCanonicalQueryParameters(nameToRemove);
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder removeAllEncodedQueryParameters(@NotNull String encodedName) {
/*      */       Intrinsics.checkNotNullParameter(encodedName, "encodedName");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-HttpUrl$Builder$removeAllEncodedQueryParameters$1 = 0;
/*      */       if ($this$apply.encodedQueryNamesAndValues == null)
/*      */         return $this$apply; 
/*      */       $this$apply.removeAllCanonicalQueryParameters(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedName, 0, 0, " \"'<>#&=", true, false, true, false, null, 211, null));
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     private final void removeAllCanonicalQueryParameters(String canonicalName) {
/*      */       Intrinsics.checkNotNull(this.encodedQueryNamesAndValues);
/*      */       int i = RangesKt.step(RangesKt.downTo(this.encodedQueryNamesAndValues.size() - 2, 0), 2).getFirst(), j = RangesKt.step(RangesKt.downTo(this.encodedQueryNamesAndValues.size() - 2, 0), 2).getLast(), k = RangesKt.step(RangesKt.downTo(this.encodedQueryNamesAndValues.size() - 2, 0), 2).getStep();
/*      */       if ((k >= 0) ? (i <= j) : (i >= j))
/*      */         while (true) {
/*      */           Intrinsics.checkNotNull(this.encodedQueryNamesAndValues);
/*      */           Intrinsics.checkNotNull(this.encodedQueryNamesAndValues);
/*      */           this.encodedQueryNamesAndValues.remove(i + 1);
/*      */           Intrinsics.checkNotNull(this.encodedQueryNamesAndValues);
/*      */           this.encodedQueryNamesAndValues.remove(i);
/*      */           Intrinsics.checkNotNull(this.encodedQueryNamesAndValues);
/*      */           if (Intrinsics.areEqual(canonicalName, this.encodedQueryNamesAndValues.get(i)) && this.encodedQueryNamesAndValues.isEmpty()) {
/*      */             this.encodedQueryNamesAndValues = (List<String>)null;
/*      */             return;
/*      */           } 
/*      */           if (i != j) {
/*      */             int m = i + k;
/*      */             continue;
/*      */           } 
/*      */           break;
/*      */         }  
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder fragment(@Nullable String fragment) {
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-HttpUrl$Builder$fragment$1 = 0;
/*      */       $this$apply.encodedFragment = (fragment != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, fragment, 0, 0, "", false, false, false, true, null, 187, null) : null;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder encodedFragment(@Nullable String encodedFragment) {
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-HttpUrl$Builder$encodedFragment$1 = 0;
/*      */       $this$apply.encodedFragment = (encodedFragment != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedFragment, 0, 0, "", true, false, false, true, null, 179, null) : null;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder reencodeForUri$okhttp() {
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-HttpUrl$Builder$reencodeForUri$1 = 0;
/*      */       String str1 = $this$apply.host;
/*      */       Regex regex = new Regex("[\"<>^`{|}]");
/*      */       String str2 = "";
/*      */       boolean bool3 = false;
/*      */       $this$apply.host = ($this$apply.host != null) ? regex.replace(str1, str2) : null;
/*      */       int i;
/*      */       for (byte b = 0; b < i; b++)
/*      */         $this$apply.encodedPathSegments.set(b, HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, $this$apply.encodedPathSegments.get(b), 0, 0, "[]", true, true, false, false, null, 227, null)); 
/*      */       List<String> encodedQueryNamesAndValues = $this$apply.encodedQueryNamesAndValues;
/*      */       if (encodedQueryNamesAndValues != null) {
/*      */         int j;
/*      */         for (i = 0, j = encodedQueryNamesAndValues.size(); i < j; i++) {
/*      */           (String)encodedQueryNamesAndValues.get(i);
/*      */           encodedQueryNamesAndValues.set(i, ((String)encodedQueryNamesAndValues.get(i) != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, encodedQueryNamesAndValues.get(i), 0, 0, "\\^`{|}", true, true, true, false, null, 195, null) : null);
/*      */         } 
/*      */       } 
/*      */       $this$apply.encodedFragment = ($this$apply.encodedFragment != null) ? HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, $this$apply.encodedFragment, 0, 0, " \"#<>\\^`{|}", true, true, false, true, null, 163, null) : null;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public String toString() {
/*      */       // Byte code:
/*      */       //   0: iconst_0
/*      */       //   1: istore_1
/*      */       //   2: iconst_0
/*      */       //   3: istore_2
/*      */       //   4: new java/lang/StringBuilder
/*      */       //   7: dup
/*      */       //   8: invokespecial <init> : ()V
/*      */       //   11: astore_2
/*      */       //   12: iconst_0
/*      */       //   13: istore_3
/*      */       //   14: iconst_0
/*      */       //   15: istore #4
/*      */       //   17: aload_2
/*      */       //   18: astore #5
/*      */       //   20: iconst_0
/*      */       //   21: istore #6
/*      */       //   23: aload_0
/*      */       //   24: getfield scheme : Ljava/lang/String;
/*      */       //   27: ifnull -> 52
/*      */       //   30: aload #5
/*      */       //   32: aload_0
/*      */       //   33: getfield scheme : Ljava/lang/String;
/*      */       //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   39: pop
/*      */       //   40: aload #5
/*      */       //   42: ldc_w '://'
/*      */       //   45: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   48: pop
/*      */       //   49: goto -> 61
/*      */       //   52: aload #5
/*      */       //   54: ldc_w '//'
/*      */       //   57: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   60: pop
/*      */       //   61: aload_0
/*      */       //   62: getfield encodedUsername : Ljava/lang/String;
/*      */       //   65: checkcast java/lang/CharSequence
/*      */       //   68: astore #7
/*      */       //   70: iconst_0
/*      */       //   71: istore #8
/*      */       //   73: aload #7
/*      */       //   75: invokeinterface length : ()I
/*      */       //   80: ifle -> 87
/*      */       //   83: iconst_1
/*      */       //   84: goto -> 88
/*      */       //   87: iconst_0
/*      */       //   88: ifne -> 121
/*      */       //   91: aload_0
/*      */       //   92: getfield encodedPassword : Ljava/lang/String;
/*      */       //   95: checkcast java/lang/CharSequence
/*      */       //   98: astore #7
/*      */       //   100: iconst_0
/*      */       //   101: istore #8
/*      */       //   103: aload #7
/*      */       //   105: invokeinterface length : ()I
/*      */       //   110: ifle -> 117
/*      */       //   113: iconst_1
/*      */       //   114: goto -> 118
/*      */       //   117: iconst_0
/*      */       //   118: ifeq -> 187
/*      */       //   121: aload #5
/*      */       //   123: aload_0
/*      */       //   124: getfield encodedUsername : Ljava/lang/String;
/*      */       //   127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   130: pop
/*      */       //   131: aload_0
/*      */       //   132: getfield encodedPassword : Ljava/lang/String;
/*      */       //   135: checkcast java/lang/CharSequence
/*      */       //   138: astore #7
/*      */       //   140: iconst_0
/*      */       //   141: istore #8
/*      */       //   143: aload #7
/*      */       //   145: invokeinterface length : ()I
/*      */       //   150: ifle -> 157
/*      */       //   153: iconst_1
/*      */       //   154: goto -> 158
/*      */       //   157: iconst_0
/*      */       //   158: ifeq -> 179
/*      */       //   161: aload #5
/*      */       //   163: bipush #58
/*      */       //   165: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*      */       //   168: pop
/*      */       //   169: aload #5
/*      */       //   171: aload_0
/*      */       //   172: getfield encodedPassword : Ljava/lang/String;
/*      */       //   175: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   178: pop
/*      */       //   179: aload #5
/*      */       //   181: bipush #64
/*      */       //   183: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*      */       //   186: pop
/*      */       //   187: aload_0
/*      */       //   188: getfield host : Ljava/lang/String;
/*      */       //   191: ifnull -> 255
/*      */       //   194: aload_0
/*      */       //   195: getfield host : Ljava/lang/String;
/*      */       //   198: dup
/*      */       //   199: invokestatic checkNotNull : (Ljava/lang/Object;)V
/*      */       //   202: checkcast java/lang/CharSequence
/*      */       //   205: bipush #58
/*      */       //   207: iconst_0
/*      */       //   208: iconst_2
/*      */       //   209: aconst_null
/*      */       //   210: invokestatic contains$default : (Ljava/lang/CharSequence;CZILjava/lang/Object;)Z
/*      */       //   213: ifeq -> 245
/*      */       //   216: aload #5
/*      */       //   218: bipush #91
/*      */       //   220: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*      */       //   223: pop
/*      */       //   224: aload #5
/*      */       //   226: aload_0
/*      */       //   227: getfield host : Ljava/lang/String;
/*      */       //   230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   233: pop
/*      */       //   234: aload #5
/*      */       //   236: bipush #93
/*      */       //   238: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*      */       //   241: pop
/*      */       //   242: goto -> 255
/*      */       //   245: aload #5
/*      */       //   247: aload_0
/*      */       //   248: getfield host : Ljava/lang/String;
/*      */       //   251: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   254: pop
/*      */       //   255: aload_0
/*      */       //   256: getfield port : I
/*      */       //   259: iconst_m1
/*      */       //   260: if_icmpne -> 270
/*      */       //   263: aload_0
/*      */       //   264: getfield scheme : Ljava/lang/String;
/*      */       //   267: ifnull -> 318
/*      */       //   270: aload_0
/*      */       //   271: invokespecial effectivePort : ()I
/*      */       //   274: istore #7
/*      */       //   276: aload_0
/*      */       //   277: getfield scheme : Ljava/lang/String;
/*      */       //   280: ifnull -> 302
/*      */       //   283: iload #7
/*      */       //   285: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
/*      */       //   288: aload_0
/*      */       //   289: getfield scheme : Ljava/lang/String;
/*      */       //   292: dup
/*      */       //   293: invokestatic checkNotNull : (Ljava/lang/Object;)V
/*      */       //   296: invokevirtual defaultPort : (Ljava/lang/String;)I
/*      */       //   299: if_icmpeq -> 318
/*      */       //   302: aload #5
/*      */       //   304: bipush #58
/*      */       //   306: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*      */       //   309: pop
/*      */       //   310: aload #5
/*      */       //   312: iload #7
/*      */       //   314: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*      */       //   317: pop
/*      */       //   318: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
/*      */       //   321: aload_0
/*      */       //   322: getfield encodedPathSegments : Ljava/util/List;
/*      */       //   325: aload #5
/*      */       //   327: invokevirtual toPathString$okhttp : (Ljava/util/List;Ljava/lang/StringBuilder;)V
/*      */       //   330: aload_0
/*      */       //   331: getfield encodedQueryNamesAndValues : Ljava/util/List;
/*      */       //   334: ifnull -> 361
/*      */       //   337: aload #5
/*      */       //   339: bipush #63
/*      */       //   341: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*      */       //   344: pop
/*      */       //   345: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
/*      */       //   348: aload_0
/*      */       //   349: getfield encodedQueryNamesAndValues : Ljava/util/List;
/*      */       //   352: dup
/*      */       //   353: invokestatic checkNotNull : (Ljava/lang/Object;)V
/*      */       //   356: aload #5
/*      */       //   358: invokevirtual toQueryString$okhttp : (Ljava/util/List;Ljava/lang/StringBuilder;)V
/*      */       //   361: aload_0
/*      */       //   362: getfield encodedFragment : Ljava/lang/String;
/*      */       //   365: ifnull -> 386
/*      */       //   368: aload #5
/*      */       //   370: bipush #35
/*      */       //   372: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*      */       //   375: pop
/*      */       //   376: aload #5
/*      */       //   378: aload_0
/*      */       //   379: getfield encodedFragment : Ljava/lang/String;
/*      */       //   382: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   385: pop
/*      */       //   386: nop
/*      */       //   387: aload_2
/*      */       //   388: invokevirtual toString : ()Ljava/lang/String;
/*      */       //   391: dup
/*      */       //   392: ldc_w 'StringBuilder().apply(builderAction).toString()'
/*      */       //   395: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*      */       //   398: areturn
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #1188	-> 0
/*      */       //   #1189	-> 23
/*      */       //   #1190	-> 30
/*      */       //   #1191	-> 40
/*      */       //   #1193	-> 52
/*      */       //   #1194	-> 61
/*      */       //   #1196	-> 61
/*      */       //   #1196	-> 88
/*      */       //   #1196	-> 118
/*      */       //   #1197	-> 121
/*      */       //   #1198	-> 131
/*      */       //   #1198	-> 158
/*      */       //   #1199	-> 161
/*      */       //   #1200	-> 169
/*      */       //   #1202	-> 179
/*      */       //   #1205	-> 187
/*      */       //   #1206	-> 194
/*      */       //   #1208	-> 216
/*      */       //   #1209	-> 224
/*      */       //   #1210	-> 234
/*      */       //   #1212	-> 245
/*      */       //   #1213	-> 255
/*      */       //   #1216	-> 255
/*      */       //   #1217	-> 270
/*      */       //   #1218	-> 276
/*      */       //   #1219	-> 302
/*      */       //   #1220	-> 310
/*      */       //   #1224	-> 318
/*      */       //   #1226	-> 330
/*      */       //   #1227	-> 337
/*      */       //   #1228	-> 345
/*      */       //   #1231	-> 361
/*      */       //   #1232	-> 368
/*      */       //   #1233	-> 376
/*      */       //   #1235	-> 386
/*      */       //   #1188	-> 387
/*      */       //   #1188	-> 398
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   276	42	7	effectivePort	I
/*      */       //   20	367	5	$this$buildString	Ljava/lang/StringBuilder;
/*      */       //   23	364	6	$i$a$-buildString-HttpUrl$Builder$toString$1	I
/*      */       //   0	399	0	this	Lokhttp3/HttpUrl$Builder;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder parse$okhttp(@Nullable HttpUrl base, @NotNull String input) {
/*      */       Intrinsics.checkNotNullParameter(input, "input");
/*      */       int pos = Util.indexOfFirstNonAsciiWhitespace$default(input, 0, 0, 3, null);
/*      */       int limit = Util.indexOfLastNonAsciiWhitespace$default(input, pos, 0, 2, null);
/*      */       int schemeDelimiterOffset = Companion.schemeDelimiterOffset(input, pos, limit);
/*      */       if (schemeDelimiterOffset != -1) {
/*      */         int i = pos;
/*      */         boolean bool = true;
/*      */         if (StringsKt.startsWith(input, "https:", i, bool)) {
/*      */           this.scheme = "https";
/*      */           pos += "https:".length();
/*      */         } else {
/*      */           i = pos;
/*      */           bool = true;
/*      */           if (StringsKt.startsWith(input, "http:", i, bool)) {
/*      */             this.scheme = "http";
/*      */             pos += "http:".length();
/*      */           } else {
/*      */             String str = input;
/*      */             bool = false;
/*      */             boolean bool1 = false;
/*      */             Intrinsics.checkNotNullExpressionValue(str.substring(bool, schemeDelimiterOffset), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*      */             throw (Throwable)new IllegalArgumentException("Expected URL scheme 'http' or 'https' but was '" + str.substring(bool, schemeDelimiterOffset) + "'");
/*      */           } 
/*      */         } 
/*      */       } else if (base != null) {
/*      */         this.scheme = base.scheme();
/*      */       } else {
/*      */         throw (Throwable)new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
/*      */       } 
/*      */       boolean hasUsername = false;
/*      */       boolean hasPassword = false;
/*      */       int slashCount = Companion.slashCount(input, pos, limit);
/*      */       if (slashCount >= 2 || base == null || (Intrinsics.areEqual(base.scheme(), this.scheme) ^ true) != 0) {
/*      */         int componentDelimiterOffset;
/*      */         pos += slashCount;
/*      */         while (true) {
/*      */           componentDelimiterOffset = Util.delimiterOffset(input, "@/\\?#", pos, limit);
/*      */           int c = (componentDelimiterOffset != limit) ? input.charAt(componentDelimiterOffset) : -1;
/*      */           switch (c) {
/*      */             case 64:
/*      */               if (!hasPassword) {
/*      */                 int passwordColonOffset = Util.delimiterOffset(input, ':', pos, componentDelimiterOffset);
/*      */                 String canonicalUsername = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos, passwordColonOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
/*      */                 this.encodedUsername = hasUsername ? (this.encodedUsername + "%40" + canonicalUsername) : canonicalUsername;
/*      */                 if (passwordColonOffset != componentDelimiterOffset) {
/*      */                   hasPassword = true;
/*      */                   this.encodedPassword = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, passwordColonOffset + 1, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
/*      */                 } 
/*      */                 hasUsername = true;
/*      */               } else {
/*      */                 this.encodedPassword += "%40" + HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
/*      */               } 
/*      */               pos = componentDelimiterOffset + 1;
/*      */             case -1:
/*      */             case 35:
/*      */             case 47:
/*      */             case 63:
/*      */             case 92:
/*      */               break;
/*      */           } 
/*      */         } 
/*      */         int portColonOffset = Companion.portColonOffset(input, pos, componentDelimiterOffset);
/*      */         if (portColonOffset + 1 < componentDelimiterOffset) {
/*      */           this.host = HostnamesKt.toCanonicalHost(HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, input, pos, portColonOffset, false, 4, null));
/*      */           this.port = Companion.parsePort(input, portColonOffset + 1, componentDelimiterOffset);
/*      */           boolean bool4 = (this.port != -1) ? true : false, bool5 = false, bool6 = false;
/*      */           if (!bool4) {
/*      */             int $i$a$-require-HttpUrl$Builder$parse$1 = 0;
/*      */             String str2 = input;
/*      */             int i = portColonOffset + 1;
/*      */             int j = componentDelimiterOffset;
/*      */             boolean bool = false;
/*      */             Intrinsics.checkNotNullExpressionValue(str2.substring(i, j), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*      */             String str1 = "Invalid URL port: \"" + str2.substring(i, j) + '"';
/*      */             throw (Throwable)new IllegalArgumentException(str1.toString());
/*      */           } 
/*      */         } else {
/*      */           this.host = HostnamesKt.toCanonicalHost(HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, input, pos, portColonOffset, false, 4, null));
/*      */           Intrinsics.checkNotNull(this.scheme);
/*      */           this.port = HttpUrl.Companion.defaultPort(this.scheme);
/*      */         } 
/*      */         boolean bool1 = (this.host != null) ? true : false, bool2 = false, bool3 = false;
/*      */         if (!bool1) {
/*      */           int $i$a$-require-HttpUrl$Builder$parse$2 = 0;
/*      */           String str2 = input;
/*      */           int i = pos, j = portColonOffset;
/*      */           boolean bool = false;
/*      */           Intrinsics.checkNotNullExpressionValue(str2.substring(i, j), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*      */           String str1 = "Invalid URL host: \"" + str2.substring(i, j) + '"';
/*      */           throw (Throwable)new IllegalArgumentException(str1.toString());
/*      */         } 
/*      */         pos = componentDelimiterOffset;
/*      */       } else {
/*      */         this.encodedUsername = base.encodedUsername();
/*      */         this.encodedPassword = base.encodedPassword();
/*      */         this.host = base.host();
/*      */         this.port = base.port();
/*      */         this.encodedPathSegments.clear();
/*      */         this.encodedPathSegments.addAll(base.encodedPathSegments());
/*      */         if (pos == limit || input.charAt(pos) == '#')
/*      */           encodedQuery(base.encodedQuery()); 
/*      */       } 
/*      */       int pathDelimiterOffset = Util.delimiterOffset(input, "?#", pos, limit);
/*      */       resolvePath(input, pos, pathDelimiterOffset);
/*      */       pos = pathDelimiterOffset;
/*      */       if (pos < limit && input.charAt(pos) == '?') {
/*      */         int queryDelimiterOffset = Util.delimiterOffset(input, '#', pos, limit);
/*      */         this.encodedQueryNamesAndValues = HttpUrl.Companion.toQueryNamesAndValues$okhttp(HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos + 1, queryDelimiterOffset, " \"'<>#", true, false, true, false, null, 208, null));
/*      */         pos = queryDelimiterOffset;
/*      */       } 
/*      */       if (pos < limit && input.charAt(pos) == '#')
/*      */         this.encodedFragment = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos + 1, limit, "", true, false, false, true, null, 176, null); 
/*      */       return this;
/*      */     }
/*      */     
/*      */     private final void resolvePath(String input, int startPos, int limit) {
/*      */       int pos = startPos;
/*      */       if (pos == limit)
/*      */         return; 
/*      */       char c = input.charAt(pos);
/*      */       if (c == '/' || c == '\\') {
/*      */         this.encodedPathSegments.clear();
/*      */         this.encodedPathSegments.add("");
/*      */         pos++;
/*      */       } else {
/*      */         this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
/*      */       } 
/*      */       int i = pos;
/*      */       while (i < limit) {
/*      */         int pathSegmentDelimiterOffset = Util.delimiterOffset(input, "/\\", i, limit);
/*      */         boolean segmentHasTrailingSlash = (pathSegmentDelimiterOffset < limit);
/*      */         push(input, i, pathSegmentDelimiterOffset, segmentHasTrailingSlash, true);
/*      */         i = pathSegmentDelimiterOffset;
/*      */         if (segmentHasTrailingSlash)
/*      */           i++; 
/*      */       } 
/*      */     }
/*      */     
/*      */     private final void push(String input, int pos, int limit, boolean addTrailingSlash, boolean alreadyEncoded) {
/*      */       String segment = HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, input, pos, limit, " \"<>^`{}|/\\?#", alreadyEncoded, false, false, false, null, 240, null);
/*      */       if (isDot(segment))
/*      */         return; 
/*      */       if (isDotDot(segment)) {
/*      */         pop();
/*      */         return;
/*      */       } 
/*      */       CharSequence charSequence = this.encodedPathSegments.get(this.encodedPathSegments.size() - 1);
/*      */       boolean bool = false;
/*      */       if ((charSequence.length() == 0)) {
/*      */         this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, segment);
/*      */       } else {
/*      */         this.encodedPathSegments.add(segment);
/*      */       } 
/*      */       if (addTrailingSlash)
/*      */         this.encodedPathSegments.add(""); 
/*      */     }
/*      */     
/*      */     private final boolean isDot(String input) {
/*      */       return (Intrinsics.areEqual(input, ".") || StringsKt.equals(input, "%2e", true));
/*      */     }
/*      */     
/*      */     private final boolean isDotDot(String input) {
/*      */       return (Intrinsics.areEqual(input, "..") || StringsKt.equals(input, "%2e.", true) || StringsKt.equals(input, ".%2e", true) || StringsKt.equals(input, "%2e%2e", true));
/*      */     }
/*      */     
/*      */     private final void pop() {
/*      */       String removed = this.encodedPathSegments.remove(this.encodedPathSegments.size() - 1);
/*      */       String str1 = removed;
/*      */       boolean bool = false;
/*      */       if ((str1.length() == 0)) {
/*      */         List<String> list = this.encodedPathSegments;
/*      */         bool = false;
/*      */         if (!list.isEmpty()) {
/*      */           this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
/*      */           return;
/*      */         } 
/*      */       } 
/*      */       this.encodedPathSegments.add("");
/*      */     }
/*      */     
/*      */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\002\b\007\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J \020\005\032\0020\0062\006\020\007\032\0020\0042\006\020\b\032\0020\0062\006\020\t\032\0020\006H\002J \020\n\032\0020\0062\006\020\007\032\0020\0042\006\020\b\032\0020\0062\006\020\t\032\0020\006H\002J \020\013\032\0020\0062\006\020\007\032\0020\0042\006\020\b\032\0020\0062\006\020\t\032\0020\006H\002J\034\020\f\032\0020\006*\0020\0042\006\020\b\032\0020\0062\006\020\t\032\0020\006H\002R\016\020\003\032\0020\004XT¢\006\002\n\000¨\006\r"}, d2 = {"Lokhttp3/HttpUrl$Builder$Companion;", "", "()V", "INVALID_HOST", "", "parsePort", "", "input", "pos", "limit", "portColonOffset", "schemeDelimiterOffset", "slashCount", "okhttp"})
/*      */     public static final class Companion {
/*      */       private Companion() {}
/*      */       
/*      */       private final int schemeDelimiterOffset(String input, int pos, int limit) {
/*      */         // Byte code:
/*      */         //   0: iload_3
/*      */         //   1: iload_2
/*      */         //   2: isub
/*      */         //   3: iconst_2
/*      */         //   4: if_icmpge -> 9
/*      */         //   7: iconst_m1
/*      */         //   8: ireturn
/*      */         //   9: aload_1
/*      */         //   10: iload_2
/*      */         //   11: invokevirtual charAt : (I)C
/*      */         //   14: istore #4
/*      */         //   16: iload #4
/*      */         //   18: bipush #97
/*      */         //   20: invokestatic compare : (II)I
/*      */         //   23: iflt -> 36
/*      */         //   26: iload #4
/*      */         //   28: bipush #122
/*      */         //   30: invokestatic compare : (II)I
/*      */         //   33: ifle -> 58
/*      */         //   36: iload #4
/*      */         //   38: bipush #65
/*      */         //   40: invokestatic compare : (II)I
/*      */         //   43: iflt -> 56
/*      */         //   46: iload #4
/*      */         //   48: bipush #90
/*      */         //   50: invokestatic compare : (II)I
/*      */         //   53: ifle -> 58
/*      */         //   56: iconst_m1
/*      */         //   57: ireturn
/*      */         //   58: iload_2
/*      */         //   59: iconst_1
/*      */         //   60: iadd
/*      */         //   61: istore #5
/*      */         //   63: iload_3
/*      */         //   64: istore #6
/*      */         //   66: iload #5
/*      */         //   68: iload #6
/*      */         //   70: if_icmpge -> 206
/*      */         //   73: aload_1
/*      */         //   74: iload #5
/*      */         //   76: invokevirtual charAt : (I)C
/*      */         //   79: istore #7
/*      */         //   81: bipush #122
/*      */         //   83: bipush #97
/*      */         //   85: iload #7
/*      */         //   87: istore #8
/*      */         //   89: iload #8
/*      */         //   91: if_icmple -> 98
/*      */         //   94: pop
/*      */         //   95: goto -> 106
/*      */         //   98: iload #8
/*      */         //   100: if_icmplt -> 106
/*      */         //   103: goto -> 200
/*      */         //   106: bipush #90
/*      */         //   108: bipush #65
/*      */         //   110: iload #7
/*      */         //   112: istore #8
/*      */         //   114: iload #8
/*      */         //   116: if_icmple -> 123
/*      */         //   119: pop
/*      */         //   120: goto -> 131
/*      */         //   123: iload #8
/*      */         //   125: if_icmplt -> 131
/*      */         //   128: goto -> 200
/*      */         //   131: bipush #57
/*      */         //   133: bipush #48
/*      */         //   135: iload #7
/*      */         //   137: istore #8
/*      */         //   139: iload #8
/*      */         //   141: if_icmple -> 148
/*      */         //   144: pop
/*      */         //   145: goto -> 156
/*      */         //   148: iload #8
/*      */         //   150: if_icmplt -> 156
/*      */         //   153: goto -> 200
/*      */         //   156: iload #7
/*      */         //   158: bipush #43
/*      */         //   160: if_icmpne -> 166
/*      */         //   163: goto -> 200
/*      */         //   166: iload #7
/*      */         //   168: bipush #45
/*      */         //   170: if_icmpne -> 176
/*      */         //   173: goto -> 200
/*      */         //   176: iload #7
/*      */         //   178: bipush #46
/*      */         //   180: if_icmpne -> 186
/*      */         //   183: goto -> 200
/*      */         //   186: iload #7
/*      */         //   188: bipush #58
/*      */         //   190: if_icmpne -> 198
/*      */         //   193: iload #5
/*      */         //   195: goto -> 199
/*      */         //   198: iconst_m1
/*      */         //   199: ireturn
/*      */         //   200: iinc #5, 1
/*      */         //   203: goto -> 66
/*      */         //   206: iconst_m1
/*      */         //   207: ireturn
/*      */         // Line number table:
/*      */         //   Java source line number -> byte code offset
/*      */         //   #1490	-> 0
/*      */         //   #1492	-> 9
/*      */         //   #1493	-> 16
/*      */         //   #1495	-> 58
/*      */         //   #1495	-> 66
/*      */         //   #1496	-> 73
/*      */         //   #1498	-> 81
/*      */         //   #1501	-> 186
/*      */         //   #1504	-> 198
/*      */         //   #1496	-> 199
/*      */         //   #1495	-> 200
/*      */         //   #1508	-> 206
/*      */         // Local variable table:
/*      */         //   start	length	slot	name	descriptor
/*      */         //   73	130	5	i	I
/*      */         //   16	192	4	c0	C
/*      */         //   0	208	0	this	Lokhttp3/HttpUrl$Builder$Companion;
/*      */         //   0	208	1	input	Ljava/lang/String;
/*      */         //   0	208	2	pos	I
/*      */         //   0	208	3	limit	I
/*      */       }
/*      */       
/*      */       private final int slashCount(String $this$slashCount, int pos, int limit) {
/*      */         int slashCount = 0;
/*      */         for (int i = pos, j = limit; i < j; ) {
/*      */           char c = $this$slashCount.charAt(i);
/*      */           if (c == '\\' || c == '/') {
/*      */             slashCount++;
/*      */             i++;
/*      */           } 
/*      */         } 
/*      */         return slashCount;
/*      */       }
/*      */       
/*      */       private final int portColonOffset(String input, int pos, int limit) {
/*      */         int i = pos;
/*      */         while (i < limit) {
/*      */           switch (input.charAt(i)) {
/*      */             case '[':
/*      */               do {
/*      */               
/*      */               } while (++i < limit && input.charAt(i) != ']');
/*      */               break;
/*      */             case ':':
/*      */               return i;
/*      */           } 
/*      */           i++;
/*      */         } 
/*      */         return limit;
/*      */       }
/*      */       
/*      */       private final int parsePort(String input, int pos, int limit) {
/*      */         // Byte code:
/*      */         //   0: nop
/*      */         //   1: getstatic okhttp3/HttpUrl.Companion : Lokhttp3/HttpUrl$Companion;
/*      */         //   4: aload_1
/*      */         //   5: iload_2
/*      */         //   6: iload_3
/*      */         //   7: ldc ''
/*      */         //   9: iconst_0
/*      */         //   10: iconst_0
/*      */         //   11: iconst_0
/*      */         //   12: iconst_0
/*      */         //   13: aconst_null
/*      */         //   14: sipush #248
/*      */         //   17: aconst_null
/*      */         //   18: invokestatic canonicalize$okhttp$default : (Lokhttp3/HttpUrl$Companion;Ljava/lang/String;IILjava/lang/String;ZZZZLjava/nio/charset/Charset;ILjava/lang/Object;)Ljava/lang/String;
/*      */         //   21: astore #4
/*      */         //   23: aload #4
/*      */         //   25: astore #6
/*      */         //   27: iconst_0
/*      */         //   28: istore #7
/*      */         //   30: aload #6
/*      */         //   32: invokestatic parseInt : (Ljava/lang/String;)I
/*      */         //   35: istore #5
/*      */         //   37: ldc 65535
/*      */         //   39: iconst_1
/*      */         //   40: iload #5
/*      */         //   42: istore #6
/*      */         //   44: iload #6
/*      */         //   46: if_icmple -> 53
/*      */         //   49: pop
/*      */         //   50: goto -> 63
/*      */         //   53: iload #6
/*      */         //   55: if_icmplt -> 63
/*      */         //   58: iload #5
/*      */         //   60: goto -> 64
/*      */         //   63: iconst_m1
/*      */         //   64: istore #4
/*      */         //   66: goto -> 74
/*      */         //   69: astore #5
/*      */         //   71: iconst_m1
/*      */         //   72: istore #4
/*      */         //   74: iload #4
/*      */         //   76: ireturn
/*      */         // Line number table:
/*      */         //   Java source line number -> byte code offset
/*      */         //   #1543	-> 0
/*      */         //   #1545	-> 1
/*      */         //   #1546	-> 23
/*      */         //   #1546	-> 35
/*      */         //   #1547	-> 37
/*      */         //   #1548	-> 69
/*      */         //   #1549	-> 71
/*      */         //   #1543	-> 74
/*      */         // Local variable table:
/*      */         //   start	length	slot	name	descriptor
/*      */         //   37	27	5	i	I
/*      */         //   23	41	4	portString	Ljava/lang/String;
/*      */         //   71	3	5	_	Ljava/lang/NumberFormatException;
/*      */         //   0	77	0	this	Lokhttp3/HttpUrl$Builder$Companion;
/*      */         //   0	77	1	input	Ljava/lang/String;
/*      */         //   0	77	2	pos	I
/*      */         //   0	77	3	limit	I
/*      */         // Exception table:
/*      */         //   from	to	target	type
/*      */         //   0	66	69	java/lang/NumberFormatException
/*      */       }
/*      */     } }
/*      */   
/*      */   public static final Companion Companion = new Companion(null);
/*      */   
/*      */   static {
/*      */     HEX_DIGITS = new char[] { 
/*      */         '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
/*      */         'A', 'B', 'C', 'D', 'E', 'F' };
/*      */   }
/*      */   
/*      */   @JvmStatic
/*      */   public static final int defaultPort(@NotNull String scheme) {
/*      */     return Companion.defaultPort(scheme);
/*      */   }
/*      */   
/*      */   @JvmStatic
/*      */   @JvmName(name = "get")
/*      */   @NotNull
/*      */   public static final HttpUrl get(@NotNull String $this$toHttpUrl) {
/*      */     return Companion.get($this$toHttpUrl);
/*      */   }
/*      */   
/*      */   @JvmStatic
/*      */   @JvmName(name = "parse")
/*      */   @Nullable
/*      */   public static final HttpUrl parse(@NotNull String $this$toHttpUrlOrNull) {
/*      */     return Companion.parse($this$toHttpUrlOrNull);
/*      */   }
/*      */   
/*      */   @JvmStatic
/*      */   @JvmName(name = "get")
/*      */   @Nullable
/*      */   public static final HttpUrl get(@NotNull URL $this$toHttpUrlOrNull) {
/*      */     return Companion.get($this$toHttpUrlOrNull);
/*      */   }
/*      */   
/*      */   @JvmStatic
/*      */   @JvmName(name = "get")
/*      */   @Nullable
/*      */   public static final HttpUrl get(@NotNull URI $this$toHttpUrlOrNull) {
/*      */     return Companion.get($this$toHttpUrlOrNull);
/*      */   }
/*      */   
/*      */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000p\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\003\n\002\020\031\n\002\b\t\n\002\020\b\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\007\n\002\020\013\n\002\b\004\n\002\030\002\n\002\b\007\n\002\020\002\n\002\020 \n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020!\n\002\b\004\n\002\030\002\n\002\b\004\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\020\020\021\032\0020\0222\006\020\023\032\0020\004H\007J\027\020\024\032\004\030\0010\0252\006\020\026\032\0020\027H\007¢\006\002\b\030J\027\020\024\032\004\030\0010\0252\006\020\031\032\0020\032H\007¢\006\002\b\030J\025\020\024\032\0020\0252\006\020\031\032\0020\004H\007¢\006\002\b\030J\027\020\033\032\004\030\0010\0252\006\020\031\032\0020\004H\007¢\006\002\b\034Ja\020\035\032\0020\004*\0020\0042\b\b\002\020\036\032\0020\0222\b\b\002\020\037\032\0020\0222\006\020 \032\0020\0042\b\b\002\020!\032\0020\"2\b\b\002\020#\032\0020\"2\b\b\002\020$\032\0020\"2\b\b\002\020%\032\0020\"2\n\b\002\020&\032\004\030\0010'H\000¢\006\002\b(J\034\020)\032\0020\"*\0020\0042\006\020\036\032\0020\0222\006\020\037\032\0020\022H\002J/\020*\032\0020\004*\0020\0042\b\b\002\020\036\032\0020\0222\b\b\002\020\037\032\0020\0222\b\b\002\020$\032\0020\"H\000¢\006\002\b+J\021\020,\032\0020\025*\0020\004H\007¢\006\002\b\024J\023\020-\032\004\030\0010\025*\0020\027H\007¢\006\002\b\024J\023\020-\032\004\030\0010\025*\0020\032H\007¢\006\002\b\024J\023\020-\032\004\030\0010\025*\0020\004H\007¢\006\002\b\033J#\020.\032\0020/*\b\022\004\022\0020\004002\n\0201\032\00602j\002`3H\000¢\006\002\b4J\031\0205\032\n\022\006\022\004\030\0010\00406*\0020\004H\000¢\006\002\b7J%\0208\032\0020/*\n\022\006\022\004\030\0010\004002\n\0201\032\00602j\002`3H\000¢\006\002\b9JV\020:\032\0020/*\0020;2\006\020<\032\0020\0042\006\020\036\032\0020\0222\006\020\037\032\0020\0222\006\020 \032\0020\0042\006\020!\032\0020\"2\006\020#\032\0020\"2\006\020$\032\0020\"2\006\020%\032\0020\"2\b\020&\032\004\030\0010'H\002J,\020=\032\0020/*\0020;2\006\020>\032\0020\0042\006\020\036\032\0020\0222\006\020\037\032\0020\0222\006\020$\032\0020\"H\002R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\004XT¢\006\002\n\000R\016\020\006\032\0020\004XT¢\006\002\n\000R\016\020\007\032\0020\bX\004¢\006\002\n\000R\016\020\t\032\0020\004XT¢\006\002\n\000R\016\020\n\032\0020\004XT¢\006\002\n\000R\016\020\013\032\0020\004XT¢\006\002\n\000R\016\020\f\032\0020\004XT¢\006\002\n\000R\016\020\r\032\0020\004XT¢\006\002\n\000R\016\020\016\032\0020\004XT¢\006\002\n\000R\016\020\017\032\0020\004XT¢\006\002\n\000R\016\020\020\032\0020\004XT¢\006\002\n\000¨\006?"}, d2 = {"Lokhttp3/HttpUrl$Companion;", "", "()V", "FORM_ENCODE_SET", "", "FRAGMENT_ENCODE_SET", "FRAGMENT_ENCODE_SET_URI", "HEX_DIGITS", "", "PASSWORD_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET_URI", "QUERY_COMPONENT_ENCODE_SET", "QUERY_COMPONENT_ENCODE_SET_URI", "QUERY_COMPONENT_REENCODE_SET", "QUERY_ENCODE_SET", "USERNAME_ENCODE_SET", "defaultPort", "", "scheme", "get", "Lokhttp3/HttpUrl;", "uri", "Ljava/net/URI;", "-deprecated_get", "url", "Ljava/net/URL;", "parse", "-deprecated_parse", "canonicalize", "pos", "limit", "encodeSet", "alreadyEncoded", "", "strict", "plusIsSpace", "unicodeAllowed", "charset", "Ljava/nio/charset/Charset;", "canonicalize$okhttp", "isPercentEncoded", "percentDecode", "percentDecode$okhttp", "toHttpUrl", "toHttpUrlOrNull", "toPathString", "", "", "out", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "toPathString$okhttp", "toQueryNamesAndValues", "", "toQueryNamesAndValues$okhttp", "toQueryString", "toQueryString$okhttp", "writeCanonicalized", "Lokio/Buffer;", "input", "writePercentDecoded", "encoded", "okhttp"})
/*      */   public static final class Companion {
/*      */     private Companion() {}
/*      */     
/*      */     @JvmStatic
/*      */     public final int defaultPort(@NotNull String scheme) {
/*      */       Intrinsics.checkNotNullParameter(scheme, "scheme");
/*      */       String str = scheme;
/*      */       switch (str.hashCode()) {
/*      */         case 3213448:
/*      */           if (str.equals("http"));
/*      */           break;
/*      */         case 99617003:
/*      */           if (str.equals("https"));
/*      */           break;
/*      */       } 
/*      */       return -1;
/*      */     }
/*      */     
/*      */     public final void toPathString$okhttp(@NotNull List<String> $this$toPathString, @NotNull StringBuilder out) {
/*      */       Intrinsics.checkNotNullParameter($this$toPathString, "$this$toPathString");
/*      */       Intrinsics.checkNotNullParameter(out, "out");
/*      */       byte b;
/*      */       int i;
/*      */       for (b = 0, i = $this$toPathString.size(); b < i; b++) {
/*      */         out.append('/');
/*      */         out.append($this$toPathString.get(b));
/*      */       } 
/*      */     }
/*      */     
/*      */     public final void toQueryString$okhttp(@NotNull List<String> $this$toQueryString, @NotNull StringBuilder out) {
/*      */       Intrinsics.checkNotNullParameter($this$toQueryString, "$this$toQueryString");
/*      */       Intrinsics.checkNotNullParameter(out, "out");
/*      */       int i = RangesKt.step((IntProgression)RangesKt.until(0, $this$toQueryString.size()), 2).getFirst(), j = RangesKt.step((IntProgression)RangesKt.until(0, $this$toQueryString.size()), 2).getLast(), k = RangesKt.step((IntProgression)RangesKt.until(0, $this$toQueryString.size()), 2).getStep();
/*      */       if ((k >= 0) ? (i <= j) : (i >= j))
/*      */         while (true) {
/*      */           String name = $this$toQueryString.get(i);
/*      */           String value = $this$toQueryString.get(i + 1);
/*      */           if (i > 0)
/*      */             out.append('&'); 
/*      */           out.append(name);
/*      */           if (value != null) {
/*      */             out.append('=');
/*      */             out.append(value);
/*      */           } 
/*      */           if (i != j) {
/*      */             int m = i + k;
/*      */             continue;
/*      */           } 
/*      */           break;
/*      */         }  
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final List<String> toQueryNamesAndValues$okhttp(@NotNull String $this$toQueryNamesAndValues) {
/*      */       Intrinsics.checkNotNullParameter($this$toQueryNamesAndValues, "$this$toQueryNamesAndValues");
/*      */       boolean bool = false;
/*      */       List<String> result = new ArrayList();
/*      */       int pos = 0;
/*      */       while (pos <= $this$toQueryNamesAndValues.length()) {
/*      */         int ampersandOffset = StringsKt.indexOf$default($this$toQueryNamesAndValues, '&', pos, false, 4, null);
/*      */         if (ampersandOffset == -1)
/*      */           ampersandOffset = $this$toQueryNamesAndValues.length(); 
/*      */         int equalsOffset = StringsKt.indexOf$default($this$toQueryNamesAndValues, '=', pos, false, 4, null);
/*      */         if (equalsOffset == -1 || equalsOffset > ampersandOffset) {
/*      */           String str = $this$toQueryNamesAndValues;
/*      */           boolean bool1 = false;
/*      */           Intrinsics.checkNotNullExpressionValue(str.substring(pos, ampersandOffset), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*      */           result.add(str.substring(pos, ampersandOffset));
/*      */           result.add(null);
/*      */         } else {
/*      */           String str = $this$toQueryNamesAndValues;
/*      */           int i = 0;
/*      */           Intrinsics.checkNotNullExpressionValue(str.substring(pos, equalsOffset), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*      */           result.add(str.substring(pos, equalsOffset));
/*      */           str = $this$toQueryNamesAndValues;
/*      */           i = equalsOffset + 1;
/*      */           boolean bool1 = false;
/*      */           Intrinsics.checkNotNullExpressionValue(str.substring(i, ampersandOffset), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*      */           result.add(str.substring(i, ampersandOffset));
/*      */         } 
/*      */         pos = ampersandOffset + 1;
/*      */       } 
/*      */       return result;
/*      */     }
/*      */     
/*      */     @JvmStatic
/*      */     @JvmName(name = "get")
/*      */     @NotNull
/*      */     public final HttpUrl get(@NotNull String $this$toHttpUrl) {
/*      */       Intrinsics.checkNotNullParameter($this$toHttpUrl, "$this$toHttpUrl");
/*      */       return (new HttpUrl.Builder()).parse$okhttp(null, $this$toHttpUrl).build();
/*      */     }
/*      */     
/*      */     @JvmStatic
/*      */     @JvmName(name = "parse")
/*      */     @Nullable
/*      */     public final HttpUrl parse(@NotNull String $this$toHttpUrlOrNull) {
/*      */       HttpUrl httpUrl;
/*      */       Intrinsics.checkNotNullParameter($this$toHttpUrlOrNull, "$this$toHttpUrlOrNull");
/*      */       try {
/*      */         httpUrl = get($this$toHttpUrlOrNull);
/*      */       } catch (IllegalArgumentException _) {
/*      */         httpUrl = null;
/*      */       } 
/*      */       return httpUrl;
/*      */     }
/*      */     
/*      */     @JvmStatic
/*      */     @JvmName(name = "get")
/*      */     @Nullable
/*      */     public final HttpUrl get(@NotNull URL $this$toHttpUrlOrNull) {
/*      */       Intrinsics.checkNotNullParameter($this$toHttpUrlOrNull, "$this$toHttpUrlOrNull");
/*      */       Intrinsics.checkNotNullExpressionValue($this$toHttpUrlOrNull.toString(), "toString()");
/*      */       return parse($this$toHttpUrlOrNull.toString());
/*      */     }
/*      */     
/*      */     @JvmStatic
/*      */     @JvmName(name = "get")
/*      */     @Nullable
/*      */     public final HttpUrl get(@NotNull URI $this$toHttpUrlOrNull) {
/*      */       Intrinsics.checkNotNullParameter($this$toHttpUrlOrNull, "$this$toHttpUrlOrNull");
/*      */       Intrinsics.checkNotNullExpressionValue($this$toHttpUrlOrNull.toString(), "toString()");
/*      */       return parse($this$toHttpUrlOrNull.toString());
/*      */     }
/*      */     
/*      */     @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.HttpUrl.Companion.toHttpUrl"}, expression = "url.toHttpUrl()"), level = DeprecationLevel.ERROR)
/*      */     @JvmName(name = "-deprecated_get")
/*      */     @NotNull
/*      */     public final HttpUrl -deprecated_get(@NotNull String url) {
/*      */       Intrinsics.checkNotNullParameter(url, "url");
/*      */       return get(url);
/*      */     }
/*      */     
/*      */     @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}, expression = "url.toHttpUrlOrNull()"), level = DeprecationLevel.ERROR)
/*      */     @JvmName(name = "-deprecated_parse")
/*      */     @Nullable
/*      */     public final HttpUrl -deprecated_parse(@NotNull String url) {
/*      */       Intrinsics.checkNotNullParameter(url, "url");
/*      */       return parse(url);
/*      */     }
/*      */     
/*      */     @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}, expression = "url.toHttpUrlOrNull()"), level = DeprecationLevel.ERROR)
/*      */     @JvmName(name = "-deprecated_get")
/*      */     @Nullable
/*      */     public final HttpUrl -deprecated_get(@NotNull URL url) {
/*      */       Intrinsics.checkNotNullParameter(url, "url");
/*      */       return get(url);
/*      */     }
/*      */     
/*      */     @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}, expression = "uri.toHttpUrlOrNull()"), level = DeprecationLevel.ERROR)
/*      */     @JvmName(name = "-deprecated_get")
/*      */     @Nullable
/*      */     public final HttpUrl -deprecated_get(@NotNull URI uri) {
/*      */       Intrinsics.checkNotNullParameter(uri, "uri");
/*      */       return get(uri);
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final String percentDecode$okhttp(@NotNull String $this$percentDecode, int pos, int limit, boolean plusIsSpace) {
/*      */       Intrinsics.checkNotNullParameter($this$percentDecode, "$this$percentDecode");
/*      */       int j;
/*      */       for (int i = pos; i < j; i++) {
/*      */         char c = $this$percentDecode.charAt(i);
/*      */         if (c == '%' || (c == '+' && plusIsSpace)) {
/*      */           Buffer out = new Buffer();
/*      */           out.writeUtf8($this$percentDecode, pos, i);
/*      */           writePercentDecoded(out, $this$percentDecode, i, limit, plusIsSpace);
/*      */           return out.readUtf8();
/*      */         } 
/*      */       } 
/*      */       String str = $this$percentDecode;
/*      */       j = 0;
/*      */       Intrinsics.checkNotNullExpressionValue(str.substring(pos, limit), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*      */       return str.substring(pos, limit);
/*      */     }
/*      */     
/*      */     private final void writePercentDecoded(Buffer $this$writePercentDecoded, String encoded, int pos, int limit, boolean plusIsSpace) {
/*      */       int codePoint = 0;
/*      */       int i = pos;
/*      */       while (i < limit) {
/*      */         String str = encoded;
/*      */         boolean bool = false;
/*      */         if (str == null)
/*      */           throw new NullPointerException("null cannot be cast to non-null type java.lang.String"); 
/*      */         codePoint = str.codePointAt(i);
/*      */         if (codePoint == 37 && i + 2 < limit) {
/*      */           int d1 = Util.parseHexDigit(encoded.charAt(i + 1));
/*      */           int d2 = Util.parseHexDigit(encoded.charAt(i + 2));
/*      */           if (d1 != -1 && d2 != -1) {
/*      */             $this$writePercentDecoded.writeByte((d1 << 4) + d2);
/*      */             i += 2;
/*      */             i += Character.charCount(codePoint);
/*      */             continue;
/*      */           } 
/*      */         } else if (codePoint == 43 && plusIsSpace) {
/*      */           $this$writePercentDecoded.writeByte(32);
/*      */           i++;
/*      */           continue;
/*      */         } 
/*      */         $this$writePercentDecoded.writeUtf8CodePoint(codePoint);
/*      */         i += Character.charCount(codePoint);
/*      */       } 
/*      */     }
/*      */     
/*      */     private final boolean isPercentEncoded(String $this$isPercentEncoded, int pos, int limit) {
/*      */       return (pos + 2 < limit && $this$isPercentEncoded.charAt(pos) == '%' && Util.parseHexDigit($this$isPercentEncoded.charAt(pos + 1)) != -1 && Util.parseHexDigit($this$isPercentEncoded.charAt(pos + 2)) != -1);
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final String canonicalize$okhttp(@NotNull String $this$canonicalize, int pos, int limit, @NotNull String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean unicodeAllowed, @Nullable Charset charset) {
/*      */       Intrinsics.checkNotNullParameter($this$canonicalize, "$this$canonicalize");
/*      */       Intrinsics.checkNotNullParameter(encodeSet, "encodeSet");
/*      */       int codePoint = 0;
/*      */       int i = pos;
/*      */       while (i < limit) {
/*      */         String str1 = $this$canonicalize;
/*      */         boolean bool1 = false;
/*      */         codePoint = str1.codePointAt(i);
/*      */         if (codePoint < 32 || codePoint == 127 || (codePoint >= 128 && !unicodeAllowed) || StringsKt.contains$default(encodeSet, (char)codePoint, false, 2, null) || (codePoint == 37 && (!alreadyEncoded || (strict && !isPercentEncoded($this$canonicalize, i, limit)))) || (codePoint == 43 && plusIsSpace)) {
/*      */           Buffer out = new Buffer();
/*      */           out.writeUtf8($this$canonicalize, pos, i);
/*      */           writeCanonicalized(out, $this$canonicalize, i, limit, encodeSet, alreadyEncoded, strict, plusIsSpace, unicodeAllowed, charset);
/*      */           return out.readUtf8();
/*      */         } 
/*      */         i += Character.charCount(codePoint);
/*      */       } 
/*      */       String str = $this$canonicalize;
/*      */       boolean bool = false;
/*      */       Intrinsics.checkNotNullExpressionValue(str.substring(pos, limit), "(this as java.lang.Strin…ing(startIndex, endIndex)");
/*      */       return str.substring(pos, limit);
/*      */     }
/*      */     
/*      */     private final void writeCanonicalized(Buffer $this$writeCanonicalized, String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean strict, boolean plusIsSpace, boolean unicodeAllowed, Charset charset) {
/*      */       Buffer encodedCharBuffer = (Buffer)null;
/*      */       int codePoint = 0;
/*      */       int i = pos;
/*      */       while (i < limit) {
/*      */         String str = input;
/*      */         boolean bool = false;
/*      */         if (str == null)
/*      */           throw new NullPointerException("null cannot be cast to non-null type java.lang.String"); 
/*      */         codePoint = str.codePointAt(i);
/*      */         if (!alreadyEncoded || (codePoint != 9 && codePoint != 10 && codePoint != 12 && codePoint != 13))
/*      */           if (codePoint == 43 && plusIsSpace) {
/*      */             $this$writeCanonicalized.writeUtf8(alreadyEncoded ? "+" : "%2B");
/*      */           } else if (codePoint < 32 || codePoint == 127 || (codePoint >= 128 && !unicodeAllowed) || StringsKt.contains$default(encodeSet, (char)codePoint, false, 2, null) || (codePoint == 37 && (!alreadyEncoded || (strict && !isPercentEncoded(input, i, limit))))) {
/*      */             if (encodedCharBuffer == null)
/*      */               encodedCharBuffer = new Buffer(); 
/*      */             if (charset == null || Intrinsics.areEqual(charset, StandardCharsets.UTF_8)) {
/*      */               encodedCharBuffer.writeUtf8CodePoint(codePoint);
/*      */             } else {
/*      */               encodedCharBuffer.writeString(input, i, i + Character.charCount(codePoint), charset);
/*      */             } 
/*      */             while (!encodedCharBuffer.exhausted()) {
/*      */               int b = encodedCharBuffer.readByte() & 0xFF;
/*      */               $this$writeCanonicalized.writeByte(37);
/*      */               $this$writeCanonicalized.writeByte(HttpUrl.HEX_DIGITS[b >> 4 & 0xF]);
/*      */               $this$writeCanonicalized.writeByte(HttpUrl.HEX_DIGITS[b & 0xF]);
/*      */             } 
/*      */           } else {
/*      */             $this$writeCanonicalized.writeUtf8CodePoint(codePoint);
/*      */           }  
/*      */         i += Character.charCount(codePoint);
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/HttpUrl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */