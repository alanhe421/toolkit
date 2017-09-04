package org.jsoup.parser;

import com.iflytek.cloud.SpeechError;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.Arrays;

enum TokeniserState {
    Data {
        void read(h hVar, a aVar) {
            switch (aVar.c()) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.a(aVar.d());
                    return;
                case '&':
                    hVar.b(CharacterReferenceInData);
                    return;
                case '<':
                    hVar.b(TagOpen);
                    return;
                case '￿':
                    hVar.a(new d());
                    return;
                default:
                    hVar.a(aVar.i());
                    return;
            }
        }
    },
    CharacterReferenceInData {
        void read(h hVar, a aVar) {
            TokeniserState.readCharRef(hVar, Data);
        }
    },
    Rcdata {
        void read(h hVar, a aVar) {
            switch (aVar.c()) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    aVar.f();
                    hVar.a((char) TokeniserState.replacementChar);
                    return;
                case '&':
                    hVar.b(CharacterReferenceInRcdata);
                    return;
                case '<':
                    hVar.b(RcdataLessthanSign);
                    return;
                case '￿':
                    hVar.a(new d());
                    return;
                default:
                    hVar.a(aVar.a('&', '<', TokeniserState.nullChar));
                    return;
            }
        }
    },
    CharacterReferenceInRcdata {
        void read(h hVar, a aVar) {
            TokeniserState.readCharRef(hVar, Rcdata);
        }
    },
    Rawtext {
        void read(h hVar, a aVar) {
            TokeniserState.readData(hVar, aVar, this, RawtextLessthanSign);
        }
    },
    ScriptData {
        void read(h hVar, a aVar) {
            TokeniserState.readData(hVar, aVar, this, ScriptDataLessthanSign);
        }
    },
    PLAINTEXT {
        void read(h hVar, a aVar) {
            switch (aVar.c()) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    aVar.f();
                    hVar.a((char) TokeniserState.replacementChar);
                    return;
                case '￿':
                    hVar.a(new d());
                    return;
                default:
                    hVar.a(aVar.b((char) TokeniserState.nullChar));
                    return;
            }
        }
    },
    TagOpen {
        void read(h hVar, a aVar) {
            switch (aVar.c()) {
                case '!':
                    hVar.b(MarkupDeclarationOpen);
                    return;
                case '/':
                    hVar.b(EndTagOpen);
                    return;
                case SpeechError.TIP_ERROR_IVP_NO_ENOUGH_AUDIO /*63*/:
                    hVar.b(BogusComment);
                    return;
                default:
                    if (aVar.p()) {
                        hVar.a(true);
                        hVar.a(TagName);
                        return;
                    }
                    hVar.c((TokeniserState) this);
                    hVar.a('<');
                    hVar.a(Data);
                    return;
            }
        }
    },
    EndTagOpen {
        void read(h hVar, a aVar) {
            if (aVar.b()) {
                hVar.d(this);
                hVar.a("</");
                hVar.a(Data);
            } else if (aVar.p()) {
                hVar.a(false);
                hVar.a(TagName);
            } else if (aVar.c('>')) {
                hVar.c((TokeniserState) this);
                hVar.b(Data);
            } else {
                hVar.c((TokeniserState) this);
                hVar.b(BogusComment);
            }
        }
    },
    TagName {
        void read(h hVar, a aVar) {
            hVar.b.b(aVar.j());
            switch (aVar.d()) {
                case '\u0000':
                    hVar.b.b(TokeniserState.replacementStr);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    hVar.a(BeforeAttributeName);
                    return;
                case '/':
                    hVar.a(SelfClosingStartTag);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    return;
            }
        }
    },
    RcdataLessthanSign {
        void read(h hVar, a aVar) {
            if (aVar.c('/')) {
                hVar.h();
                hVar.b(RCDATAEndTagOpen);
            } else if (!aVar.p() || hVar.j() == null || aVar.f("</" + hVar.j())) {
                hVar.a("<");
                hVar.a(Rcdata);
            } else {
                hVar.b = hVar.a(false).a(hVar.j());
                hVar.c();
                aVar.e();
                hVar.a(Data);
            }
        }
    },
    RCDATAEndTagOpen {
        void read(h hVar, a aVar) {
            if (aVar.p()) {
                hVar.a(false);
                hVar.b.a(aVar.c());
                hVar.a.append(aVar.c());
                hVar.b(RCDATAEndTagName);
                return;
            }
            hVar.a("</");
            hVar.a(Rcdata);
        }
    },
    RCDATAEndTagName {
        void read(h hVar, a aVar) {
            if (aVar.p()) {
                String l = aVar.l();
                hVar.b.b(l);
                hVar.a.append(l);
                return;
            }
            switch (aVar.d()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    if (hVar.i()) {
                        hVar.a(BeforeAttributeName);
                        return;
                    } else {
                        anythingElse(hVar, aVar);
                        return;
                    }
                case '/':
                    if (hVar.i()) {
                        hVar.a(SelfClosingStartTag);
                        return;
                    } else {
                        anythingElse(hVar, aVar);
                        return;
                    }
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    if (hVar.i()) {
                        hVar.c();
                        hVar.a(Data);
                        return;
                    }
                    anythingElse(hVar, aVar);
                    return;
                default:
                    anythingElse(hVar, aVar);
                    return;
            }
        }

        private void anythingElse(h hVar, a aVar) {
            hVar.a("</" + hVar.a.toString());
            aVar.e();
            hVar.a(Rcdata);
        }
    },
    RawtextLessthanSign {
        void read(h hVar, a aVar) {
            if (aVar.c('/')) {
                hVar.h();
                hVar.b(RawtextEndTagOpen);
                return;
            }
            hVar.a('<');
            hVar.a(Rawtext);
        }
    },
    RawtextEndTagOpen {
        void read(h hVar, a aVar) {
            TokeniserState.readEndTag(hVar, aVar, RawtextEndTagName, Rawtext);
        }
    },
    RawtextEndTagName {
        void read(h hVar, a aVar) {
            TokeniserState.handleDataEndTag(hVar, aVar, Rawtext);
        }
    },
    ScriptDataLessthanSign {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case '!':
                    hVar.a("<!");
                    hVar.a(ScriptDataEscapeStart);
                    return;
                case '/':
                    hVar.h();
                    hVar.a(ScriptDataEndTagOpen);
                    return;
                default:
                    hVar.a("<");
                    aVar.e();
                    hVar.a(ScriptData);
                    return;
            }
        }
    },
    ScriptDataEndTagOpen {
        void read(h hVar, a aVar) {
            TokeniserState.readEndTag(hVar, aVar, ScriptDataEndTagName, ScriptData);
        }
    },
    ScriptDataEndTagName {
        void read(h hVar, a aVar) {
            TokeniserState.handleDataEndTag(hVar, aVar, ScriptData);
        }
    },
    ScriptDataEscapeStart {
        void read(h hVar, a aVar) {
            if (aVar.c('-')) {
                hVar.a('-');
                hVar.b(ScriptDataEscapeStartDash);
                return;
            }
            hVar.a(ScriptData);
        }
    },
    ScriptDataEscapeStartDash {
        void read(h hVar, a aVar) {
            if (aVar.c('-')) {
                hVar.a('-');
                hVar.b(ScriptDataEscapedDashDash);
                return;
            }
            hVar.a(ScriptData);
        }
    },
    ScriptDataEscaped {
        void read(h hVar, a aVar) {
            if (aVar.b()) {
                hVar.d(this);
                hVar.a(Data);
                return;
            }
            switch (aVar.c()) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    aVar.f();
                    hVar.a((char) TokeniserState.replacementChar);
                    return;
                case '-':
                    hVar.a('-');
                    hVar.b(ScriptDataEscapedDash);
                    return;
                case '<':
                    hVar.b(ScriptDataEscapedLessthanSign);
                    return;
                default:
                    hVar.a(aVar.a('-', '<', TokeniserState.nullChar));
                    return;
            }
        }
    },
    ScriptDataEscapedDash {
        void read(h hVar, a aVar) {
            if (aVar.b()) {
                hVar.d(this);
                hVar.a(Data);
                return;
            }
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.a((char) TokeniserState.replacementChar);
                    hVar.a(ScriptDataEscaped);
                    return;
                case '-':
                    hVar.a(d);
                    hVar.a(ScriptDataEscapedDashDash);
                    return;
                case '<':
                    hVar.a(ScriptDataEscapedLessthanSign);
                    return;
                default:
                    hVar.a(d);
                    hVar.a(ScriptDataEscaped);
                    return;
            }
        }
    },
    ScriptDataEscapedDashDash {
        void read(h hVar, a aVar) {
            if (aVar.b()) {
                hVar.d(this);
                hVar.a(Data);
                return;
            }
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.a((char) TokeniserState.replacementChar);
                    hVar.a(ScriptDataEscaped);
                    return;
                case '-':
                    hVar.a(d);
                    return;
                case '<':
                    hVar.a(ScriptDataEscapedLessthanSign);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.a(d);
                    hVar.a(ScriptData);
                    return;
                default:
                    hVar.a(d);
                    hVar.a(ScriptDataEscaped);
                    return;
            }
        }
    },
    ScriptDataEscapedLessthanSign {
        void read(h hVar, a aVar) {
            if (aVar.p()) {
                hVar.h();
                hVar.a.append(aVar.c());
                hVar.a("<" + aVar.c());
                hVar.b(ScriptDataDoubleEscapeStart);
            } else if (aVar.c('/')) {
                hVar.h();
                hVar.b(ScriptDataEscapedEndTagOpen);
            } else {
                hVar.a('<');
                hVar.a(ScriptDataEscaped);
            }
        }
    },
    ScriptDataEscapedEndTagOpen {
        void read(h hVar, a aVar) {
            if (aVar.p()) {
                hVar.a(false);
                hVar.b.a(aVar.c());
                hVar.a.append(aVar.c());
                hVar.b(ScriptDataEscapedEndTagName);
                return;
            }
            hVar.a("</");
            hVar.a(ScriptDataEscaped);
        }
    },
    ScriptDataEscapedEndTagName {
        void read(h hVar, a aVar) {
            TokeniserState.handleDataEndTag(hVar, aVar, ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscapeStart {
        void read(h hVar, a aVar) {
            TokeniserState.handleDataDoubleEscapeTag(hVar, aVar, ScriptDataDoubleEscaped, ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscaped {
        void read(h hVar, a aVar) {
            char c = aVar.c();
            switch (c) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    aVar.f();
                    hVar.a((char) TokeniserState.replacementChar);
                    return;
                case '-':
                    hVar.a(c);
                    hVar.b(ScriptDataDoubleEscapedDash);
                    return;
                case '<':
                    hVar.a(c);
                    hVar.b(ScriptDataDoubleEscapedLessthanSign);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    hVar.a(aVar.a('-', '<', TokeniserState.nullChar));
                    return;
            }
        }
    },
    ScriptDataDoubleEscapedDash {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.a((char) TokeniserState.replacementChar);
                    hVar.a(ScriptDataDoubleEscaped);
                    return;
                case '-':
                    hVar.a(d);
                    hVar.a(ScriptDataDoubleEscapedDashDash);
                    return;
                case '<':
                    hVar.a(d);
                    hVar.a(ScriptDataDoubleEscapedLessthanSign);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    hVar.a(d);
                    hVar.a(ScriptDataDoubleEscaped);
                    return;
            }
        }
    },
    ScriptDataDoubleEscapedDashDash {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.a((char) TokeniserState.replacementChar);
                    hVar.a(ScriptDataDoubleEscaped);
                    return;
                case '-':
                    hVar.a(d);
                    return;
                case '<':
                    hVar.a(d);
                    hVar.a(ScriptDataDoubleEscapedLessthanSign);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.a(d);
                    hVar.a(ScriptData);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    hVar.a(d);
                    hVar.a(ScriptDataDoubleEscaped);
                    return;
            }
        }
    },
    ScriptDataDoubleEscapedLessthanSign {
        void read(h hVar, a aVar) {
            if (aVar.c('/')) {
                hVar.a('/');
                hVar.h();
                hVar.b(ScriptDataDoubleEscapeEnd);
                return;
            }
            hVar.a(ScriptDataDoubleEscaped);
        }
    },
    ScriptDataDoubleEscapeEnd {
        void read(h hVar, a aVar) {
            TokeniserState.handleDataDoubleEscapeTag(hVar, aVar, ScriptDataEscaped, ScriptDataDoubleEscaped);
        }
    },
    BeforeAttributeName {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.b.o();
                    aVar.e();
                    hVar.a(AttributeName);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                case '\'':
                case '<':
                case '=':
                    hVar.c((TokeniserState) this);
                    hVar.b.o();
                    hVar.b.b(d);
                    hVar.a(AttributeName);
                    return;
                case '/':
                    hVar.a(SelfClosingStartTag);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    hVar.b.o();
                    aVar.e();
                    hVar.a(AttributeName);
                    return;
            }
        }
    },
    AttributeName {
        void read(h hVar, a aVar) {
            hVar.b.c(aVar.b(TokeniserState.attributeNameCharsSorted));
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.b.b((char) TokeniserState.replacementChar);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    hVar.a(AfterAttributeName);
                    return;
                case '\"':
                case '\'':
                case '<':
                    hVar.c((TokeniserState) this);
                    hVar.b.b(d);
                    return;
                case '/':
                    hVar.a(SelfClosingStartTag);
                    return;
                case '=':
                    hVar.a(BeforeAttributeValue);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    return;
            }
        }
    },
    AfterAttributeName {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.b.b((char) TokeniserState.replacementChar);
                    hVar.a(AttributeName);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                case '\'':
                case '<':
                    hVar.c((TokeniserState) this);
                    hVar.b.o();
                    hVar.b.b(d);
                    hVar.a(AttributeName);
                    return;
                case '/':
                    hVar.a(SelfClosingStartTag);
                    return;
                case '=':
                    hVar.a(BeforeAttributeValue);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    hVar.b.o();
                    aVar.e();
                    hVar.a(AttributeName);
                    return;
            }
        }
    },
    BeforeAttributeValue {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.b.c((char) TokeniserState.replacementChar);
                    hVar.a(AttributeValue_unquoted);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    hVar.a(AttributeValue_doubleQuoted);
                    return;
                case '&':
                    aVar.e();
                    hVar.a(AttributeValue_unquoted);
                    return;
                case '\'':
                    hVar.a(AttributeValue_singleQuoted);
                    return;
                case '<':
                case '=':
                case Opcodes.SGET /*96*/:
                    hVar.c((TokeniserState) this);
                    hVar.b.c(d);
                    hVar.a(AttributeValue_unquoted);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c((TokeniserState) this);
                    hVar.c();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.c();
                    hVar.a(Data);
                    return;
                default:
                    aVar.e();
                    hVar.a(AttributeValue_unquoted);
                    return;
            }
        }
    },
    AttributeValue_doubleQuoted {
        void read(h hVar, a aVar) {
            String a = aVar.a(TokeniserState.attributeDoubleValueCharsSorted);
            if (a.length() > 0) {
                hVar.b.d(a);
            } else {
                hVar.b.u();
            }
            switch (aVar.d()) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.b.c((char) TokeniserState.replacementChar);
                    return;
                case '\"':
                    hVar.a(AfterAttributeValue_quoted);
                    return;
                case '&':
                    int[] a2 = hVar.a(Character.valueOf('\"'), true);
                    if (a2 != null) {
                        hVar.b.a(a2);
                        return;
                    } else {
                        hVar.b.c('&');
                        return;
                    }
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    return;
            }
        }
    },
    AttributeValue_singleQuoted {
        void read(h hVar, a aVar) {
            String a = aVar.a(TokeniserState.attributeSingleValueCharsSorted);
            if (a.length() > 0) {
                hVar.b.d(a);
            } else {
                hVar.b.u();
            }
            switch (aVar.d()) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.b.c((char) TokeniserState.replacementChar);
                    return;
                case '&':
                    int[] a2 = hVar.a(Character.valueOf('\''), true);
                    if (a2 != null) {
                        hVar.b.a(a2);
                        return;
                    } else {
                        hVar.b.c('&');
                        return;
                    }
                case '\'':
                    hVar.a(AfterAttributeValue_quoted);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    return;
            }
        }
    },
    AttributeValue_unquoted {
        void read(h hVar, a aVar) {
            String b = aVar.b(TokeniserState.attributeValueUnquoted);
            if (b.length() > 0) {
                hVar.b.d(b);
            }
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.b.c((char) TokeniserState.replacementChar);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    hVar.a(BeforeAttributeName);
                    return;
                case '\"':
                case '\'':
                case '<':
                case '=':
                case Opcodes.SGET /*96*/:
                    hVar.c((TokeniserState) this);
                    hVar.b.c(d);
                    return;
                case '&':
                    int[] a = hVar.a(Character.valueOf('>'), true);
                    if (a != null) {
                        hVar.b.a(a);
                        return;
                    } else {
                        hVar.b.c('&');
                        return;
                    }
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    return;
            }
        }
    },
    AfterAttributeValue_quoted {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    hVar.a(BeforeAttributeName);
                    return;
                case '/':
                    hVar.a(SelfClosingStartTag);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    hVar.c((TokeniserState) this);
                    aVar.e();
                    hVar.a(BeforeAttributeName);
                    return;
            }
        }
    },
    SelfClosingStartTag {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.b.d = true;
                    hVar.c();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.a(Data);
                    return;
                default:
                    hVar.c((TokeniserState) this);
                    aVar.e();
                    hVar.a(BeforeAttributeName);
                    return;
            }
        }
    },
    BogusComment {
        void read(h hVar, a aVar) {
            aVar.e();
            Token bVar = new b();
            bVar.c = true;
            bVar.b.append(aVar.b('>'));
            hVar.a(bVar);
            hVar.b(Data);
        }
    },
    MarkupDeclarationOpen {
        void read(h hVar, a aVar) {
            if (aVar.d("--")) {
                hVar.d();
                hVar.a(CommentStart);
            } else if (aVar.e("DOCTYPE")) {
                hVar.a(Doctype);
            } else if (aVar.d("[CDATA[")) {
                hVar.a(CdataSection);
            } else {
                hVar.c((TokeniserState) this);
                hVar.b(BogusComment);
            }
        }
    },
    CommentStart {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.g.b.append(TokeniserState.replacementChar);
                    hVar.a(Comment);
                    return;
                case '-':
                    hVar.a(CommentStartDash);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c((TokeniserState) this);
                    hVar.e();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.e();
                    hVar.a(Data);
                    return;
                default:
                    hVar.g.b.append(d);
                    hVar.a(Comment);
                    return;
            }
        }
    },
    CommentStartDash {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.g.b.append(TokeniserState.replacementChar);
                    hVar.a(Comment);
                    return;
                case '-':
                    hVar.a(CommentStartDash);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c((TokeniserState) this);
                    hVar.e();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.e();
                    hVar.a(Data);
                    return;
                default:
                    hVar.g.b.append(d);
                    hVar.a(Comment);
                    return;
            }
        }
    },
    Comment {
        void read(h hVar, a aVar) {
            switch (aVar.c()) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    aVar.f();
                    hVar.g.b.append(TokeniserState.replacementChar);
                    return;
                case '-':
                    hVar.b(CommentEndDash);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.e();
                    hVar.a(Data);
                    return;
                default:
                    hVar.g.b.append(aVar.a('-', TokeniserState.nullChar));
                    return;
            }
        }
    },
    CommentEndDash {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.g.b.append('-').append(TokeniserState.replacementChar);
                    hVar.a(Comment);
                    return;
                case '-':
                    hVar.a(CommentEnd);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.e();
                    hVar.a(Data);
                    return;
                default:
                    hVar.g.b.append('-').append(d);
                    hVar.a(Comment);
                    return;
            }
        }
    },
    CommentEnd {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.g.b.append("--").append(TokeniserState.replacementChar);
                    hVar.a(Comment);
                    return;
                case '!':
                    hVar.c((TokeniserState) this);
                    hVar.a(CommentEndBang);
                    return;
                case '-':
                    hVar.c((TokeniserState) this);
                    hVar.g.b.append('-');
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.e();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.e();
                    hVar.a(Data);
                    return;
                default:
                    hVar.c((TokeniserState) this);
                    hVar.g.b.append("--").append(d);
                    hVar.a(Comment);
                    return;
            }
        }
    },
    CommentEndBang {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.g.b.append("--!").append(TokeniserState.replacementChar);
                    hVar.a(Comment);
                    return;
                case '-':
                    hVar.g.b.append("--!");
                    hVar.a(CommentEndDash);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.e();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.e();
                    hVar.a(Data);
                    return;
                default:
                    hVar.g.b.append("--!").append(d);
                    hVar.a(Comment);
                    return;
            }
        }
    },
    Doctype {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    hVar.a(BeforeDoctypeName);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    break;
                case '￿':
                    hVar.d(this);
                    break;
                default:
                    hVar.c((TokeniserState) this);
                    hVar.a(BeforeDoctypeName);
                    return;
            }
            hVar.c((TokeniserState) this);
            hVar.f();
            hVar.f.f = true;
            hVar.g();
            hVar.a(Data);
        }
    },
    BeforeDoctypeName {
        void read(h hVar, a aVar) {
            if (aVar.p()) {
                hVar.f();
                hVar.a(DoctypeName);
                return;
            }
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.f();
                    hVar.f.b.append(TokeniserState.replacementChar);
                    hVar.a(DoctypeName);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f();
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.f();
                    hVar.f.b.append(d);
                    hVar.a(DoctypeName);
                    return;
            }
        }
    },
    DoctypeName {
        void read(h hVar, a aVar) {
            if (aVar.p()) {
                hVar.f.b.append(aVar.l());
                return;
            }
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.f.b.append(TokeniserState.replacementChar);
                    return;
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    hVar.a(AfterDoctypeName);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.f.b.append(d);
                    return;
            }
        }
    },
    AfterDoctypeName {
        void read(h hVar, a aVar) {
            if (aVar.b()) {
                hVar.d(this);
                hVar.f.f = true;
                hVar.g();
                hVar.a(Data);
            } else if (aVar.c('\t', '\n', '\r', '\f', ' ')) {
                aVar.f();
            } else if (aVar.c('>')) {
                hVar.g();
                hVar.b(Data);
            } else if (aVar.e("PUBLIC")) {
                hVar.f.c = "PUBLIC";
                hVar.a(AfterDoctypePublicKeyword);
            } else if (aVar.e("SYSTEM")) {
                hVar.f.c = "SYSTEM";
                hVar.a(AfterDoctypeSystemKeyword);
            } else {
                hVar.c((TokeniserState) this);
                hVar.f.f = true;
                hVar.b(BogusDoctype);
            }
        }
    },
    AfterDoctypePublicKeyword {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    hVar.a(BeforeDoctypePublicIdentifier);
                    return;
                case '\"':
                    hVar.c((TokeniserState) this);
                    hVar.a(DoctypePublicIdentifier_doubleQuoted);
                    return;
                case '\'':
                    hVar.c((TokeniserState) this);
                    hVar.a(DoctypePublicIdentifier_singleQuoted);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.a(BogusDoctype);
                    return;
            }
        }
    },
    BeforeDoctypePublicIdentifier {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    hVar.a(DoctypePublicIdentifier_doubleQuoted);
                    return;
                case '\'':
                    hVar.a(DoctypePublicIdentifier_singleQuoted);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.a(BogusDoctype);
                    return;
            }
        }
    },
    DoctypePublicIdentifier_doubleQuoted {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.f.d.append(TokeniserState.replacementChar);
                    return;
                case '\"':
                    hVar.a(AfterDoctypePublicIdentifier);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.f.d.append(d);
                    return;
            }
        }
    },
    DoctypePublicIdentifier_singleQuoted {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.f.d.append(TokeniserState.replacementChar);
                    return;
                case '\'':
                    hVar.a(AfterDoctypePublicIdentifier);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.f.d.append(d);
                    return;
            }
        }
    },
    AfterDoctypePublicIdentifier {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    hVar.a(BetweenDoctypePublicAndSystemIdentifiers);
                    return;
                case '\"':
                    hVar.c((TokeniserState) this);
                    hVar.a(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    hVar.c((TokeniserState) this);
                    hVar.a(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.a(BogusDoctype);
                    return;
            }
        }
    },
    BetweenDoctypePublicAndSystemIdentifiers {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    hVar.c((TokeniserState) this);
                    hVar.a(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    hVar.c((TokeniserState) this);
                    hVar.a(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.a(BogusDoctype);
                    return;
            }
        }
    },
    AfterDoctypeSystemKeyword {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    hVar.a(BeforeDoctypeSystemIdentifier);
                    return;
                case '\"':
                    hVar.c((TokeniserState) this);
                    hVar.a(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    hVar.c((TokeniserState) this);
                    hVar.a(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.g();
                    return;
            }
        }
    },
    BeforeDoctypeSystemIdentifier {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case '\"':
                    hVar.a(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    hVar.a(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.a(BogusDoctype);
                    return;
            }
        }
    },
    DoctypeSystemIdentifier_doubleQuoted {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.f.e.append(TokeniserState.replacementChar);
                    return;
                case '\"':
                    hVar.a(AfterDoctypeSystemIdentifier);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.f.e.append(d);
                    return;
            }
        }
    },
    DoctypeSystemIdentifier_singleQuoted {
        void read(h hVar, a aVar) {
            char d = aVar.d();
            switch (d) {
                case '\u0000':
                    hVar.c((TokeniserState) this);
                    hVar.f.e.append(TokeniserState.replacementChar);
                    return;
                case '\'':
                    hVar.a(AfterDoctypeSystemIdentifier);
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c((TokeniserState) this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.f.e.append(d);
                    return;
            }
        }
    },
    AfterDoctypeSystemIdentifier {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    return;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.d(this);
                    hVar.f.f = true;
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    hVar.c((TokeniserState) this);
                    hVar.a(BogusDoctype);
                    return;
            }
        }
    },
    BogusDoctype {
        void read(h hVar, a aVar) {
            switch (aVar.d()) {
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.g();
                    hVar.a(Data);
                    return;
                case '￿':
                    hVar.g();
                    hVar.a(Data);
                    return;
                default:
                    return;
            }
        }
    },
    CdataSection {
        void read(h hVar, a aVar) {
            hVar.a(aVar.a("]]>"));
            aVar.d("]]>");
            hVar.a(Data);
        }
    };
    
    private static final char[] attributeDoubleValueCharsSorted = null;
    private static final char[] attributeNameCharsSorted = null;
    private static final char[] attributeSingleValueCharsSorted = null;
    private static final char[] attributeValueUnquoted = null;
    private static final char eof = '￿';
    static final char nullChar = '\u0000';
    private static final char replacementChar = '�';
    private static final String replacementStr = null;

    abstract void read(h hVar, a aVar);

    static {
        attributeSingleValueCharsSorted = new char[]{'\'', '&', nullChar};
        attributeDoubleValueCharsSorted = new char[]{'\"', '&', nullChar};
        attributeNameCharsSorted = new char[]{'\t', '\n', '\r', '\f', ' ', '/', '=', '>', nullChar, '\"', '\'', '<'};
        attributeValueUnquoted = new char[]{'\t', '\n', '\r', '\f', ' ', '&', '>', nullChar, '\"', '\'', '<', '=', '`'};
        replacementStr = String.valueOf(replacementChar);
        Arrays.sort(attributeSingleValueCharsSorted);
        Arrays.sort(attributeDoubleValueCharsSorted);
        Arrays.sort(attributeNameCharsSorted);
        Arrays.sort(attributeValueUnquoted);
    }

    private static void handleDataEndTag(h hVar, a aVar, TokeniserState tokeniserState) {
        if (aVar.p()) {
            String l = aVar.l();
            hVar.b.b(l);
            hVar.a.append(l);
            return;
        }
        Object obj = null;
        if (hVar.i() && !aVar.b()) {
            char d = aVar.d();
            switch (d) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    hVar.a(BeforeAttributeName);
                    break;
                case '/':
                    hVar.a(SelfClosingStartTag);
                    break;
                case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                    hVar.c();
                    hVar.a(Data);
                    break;
                default:
                    hVar.a.append(d);
                    obj = 1;
                    break;
            }
        }
        int i = 1;
        if (obj != null) {
            hVar.a("</" + hVar.a.toString());
            hVar.a(tokeniserState);
        }
    }

    private static void readData(h hVar, a aVar, TokeniserState tokeniserState, TokeniserState tokeniserState2) {
        switch (aVar.c()) {
            case '\u0000':
                hVar.c(tokeniserState);
                aVar.f();
                hVar.a((char) replacementChar);
                return;
            case '<':
                hVar.b(tokeniserState2);
                return;
            case '￿':
                hVar.a(new d());
                return;
            default:
                hVar.a(aVar.a('<', nullChar));
                return;
        }
    }

    private static void readCharRef(h hVar, TokeniserState tokeniserState) {
        int[] a = hVar.a(null, false);
        if (a == null) {
            hVar.a('&');
        } else {
            hVar.a(a);
        }
        hVar.a(tokeniserState);
    }

    private static void readEndTag(h hVar, a aVar, TokeniserState tokeniserState, TokeniserState tokeniserState2) {
        if (aVar.p()) {
            hVar.a(false);
            hVar.a(tokeniserState);
            return;
        }
        hVar.a("</");
        hVar.a(tokeniserState2);
    }

    private static void handleDataDoubleEscapeTag(h hVar, a aVar, TokeniserState tokeniserState, TokeniserState tokeniserState2) {
        if (aVar.p()) {
            String l = aVar.l();
            hVar.a.append(l);
            hVar.a(l);
            return;
        }
        char d = aVar.d();
        switch (d) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case '/':
            case SpeechError.TIP_ERROR_IVP_TEXT_NOT_MATCH /*62*/:
                if (hVar.a.toString().equals("script")) {
                    hVar.a(tokeniserState);
                } else {
                    hVar.a(tokeniserState2);
                }
                hVar.a(d);
                return;
            default:
                aVar.e();
                hVar.a(tokeniserState2);
                return;
        }
    }
}
