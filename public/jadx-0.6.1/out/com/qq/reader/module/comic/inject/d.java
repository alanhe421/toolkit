package com.qq.reader.module.comic.inject;

import com.tencent.feedback.proguard.R;

/* compiled from: ComicReaderResProvider */
public class d {

    /* compiled from: ComicReaderResProvider */
    public static class a implements com.qrcomic.c.c.a {
        public int a(boolean z) {
            return !z ? R.drawable.menu_icon_catalog : R.drawable.menu_icon_catalog_night;
        }

        public int b(boolean z) {
            return !z ? R.drawable.menu_icon_jump : R.drawable.menu_icon_jump_night;
        }

        public int c(boolean z) {
            return !z ? R.drawable.menu_icon_setting : R.drawable.menu_icon_setting_night;
        }

        public int d(boolean z) {
            return z ? R.color.commonsetting_bg_color_night : R.color.commonsetting_bg_color;
        }

        public int e(boolean z) {
            return z ? R.color.skin_set_read_page_menu_item_nightmode_selector : R.color.skin_set_read_page_menu_item_daymode_textcolor_selector;
        }

        public int f(boolean z) {
            return !z ? R.drawable.popup_box : R.drawable.popup_box_night;
        }

        public int[] g(boolean z) {
            int[] iArr = new int[2];
            iArr[0] = !z ? R.drawable.light_button_left : R.drawable.light_button_left_night;
            iArr[1] = !z ? R.drawable.light_button_right : R.drawable.light_button_right_night;
            return iArr;
        }

        public int h(boolean z) {
            return R.drawable.commonsetting_dialog_followsys_bg_selector_for_comic;
        }

        public int[] i(boolean z) {
            int[] iArr = new int[3];
            if (z) {
                iArr[0] = R.drawable.reader_dialog_setting_radiogroup_bg;
            } else {
                iArr[0] = R.drawable.reader_dialog_setting_radiogroup_bg;
            }
            if (z) {
                iArr[1] = R.drawable.common_radiobutton_bg_selector;
            } else {
                iArr[1] = R.drawable.common_radiobutton_bg_selector;
            }
            if (z) {
                iArr[2] = R.color.common_radiobutton_textcolor_selector;
            } else {
                iArr[2] = R.color.common_radiobutton_textcolor_selector;
            }
            return iArr;
        }

        public int[] j(boolean z) {
            int[] iArr = new int[2];
            iArr[0] = z ? R.drawable.seekbar_thumb_night : R.drawable.seekbar_thumb;
            iArr[1] = z ? R.drawable.seekbar_style_night : R.drawable.seekbar_style;
            return iArr;
        }

        public int k(boolean z) {
            return !z ? R.drawable.readerpage_comment_icon_selector : R.drawable.read_pager_end_popup_comment_bg_night_mode;
        }
    }

    /* compiled from: ComicReaderResProvider */
    public static class b implements com.qrcomic.c.c.c {
        public int a(boolean z) {
            return R.drawable.readpage_topbar_back;
        }

        public int b(boolean z) {
            return R.drawable.readerpage_download_icon_selector;
        }

        public int c(boolean z) {
            return R.drawable.readpage_topbar_more;
        }

        public int d(boolean z) {
            return z ? R.color.commonsetting_bg_color_night : R.color.commonsetting_bg_color;
        }

        public int e(boolean z) {
            return z ? R.drawable.readpage_topbar_more_popup : R.drawable.readpage_popmenu_bg;
        }

        public int f(boolean z) {
            return z ? R.drawable.readpage_topbar_read_private_night : R.drawable.readpage_topbar_read_private;
        }

        public int g(boolean z) {
            return z ? R.drawable.readpage_topbar_detail_night : R.drawable.readpage_topbar_detail;
        }

        public int h(boolean z) {
            return z ? R.color.skin_set_read_page_menu_item_nightmode_selector : R.color.skin_set_read_page_menu_item_daymode_textcolor_selector;
        }
    }

    /* compiled from: ComicReaderResProvider */
    public static class c implements com.qrcomic.c.c.b {
        public int[] a(boolean z) {
            return new int[]{R.layout.login_loading_dialog, R.id.login_loading_msg};
        }
    }
}
