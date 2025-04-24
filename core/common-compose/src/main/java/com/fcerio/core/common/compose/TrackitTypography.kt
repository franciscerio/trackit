package com.fcerio.core.common.compose

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
class TrackitTypography constructor(
    private val UrbanistRegular: FontFamily = FontFamily(Font(R.font.urbanist_regular)),
    private val UrbanistMedium: FontFamily = FontFamily(Font(R.font.urbanist_medium)),
    private val UrbanistSemiBold: FontFamily = FontFamily(Font(R.font.urbanist_semi_bold)),
    private val UrbanistBold: FontFamily = FontFamily(Font(R.font.urbanist_bold)),
    private val BeVietnamBold: FontFamily = FontFamily(Font(R.font.be_vietnam_pro_bold)),
    val largeTitle: TextStyle = DefaultTextStyle.merge(
        fontFamily = BeVietnamBold,
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp,
        lineHeight = 41.sp
    ),
    val title1: TextStyle = DefaultTextStyle.merge(
        fontFamily = BeVietnamBold,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp,
        lineHeight = 34.sp
    ),
    val title2: TextStyle = DefaultTextStyle.merge(
        fontFamily = BeVietnamBold,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.sp,
        lineHeight = 28.sp
    ),
    val title3: TextStyle = DefaultTextStyle.merge(
        fontFamily = UrbanistSemiBold,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp,
        lineHeight = 24.sp
    ),
    val headline: TextStyle = DefaultTextStyle.merge(
        fontFamily = UrbanistSemiBold,
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp,
        lineHeight = 22.sp
    ),
    val body1: TextStyle = DefaultTextStyle.merge(
        fontFamily = UrbanistRegular,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        lineHeight = 22.sp
    ),
    val body2: TextStyle = DefaultTextStyle.merge(
        fontFamily = UrbanistRegular,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        lineHeight = 22.sp
    ),
    val body3: TextStyle = DefaultTextStyle.merge(
        fontFamily = UrbanistRegular,
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        lineHeight = 22.sp
    ),
    val body1Medium: TextStyle = body1.merge(
        fontWeight = FontWeight.Medium,
        fontFamily = UrbanistMedium
    ),
    val body1Bold: TextStyle = body1.merge(
        fontWeight = FontWeight.Bold,
        fontFamily = UrbanistBold,
        fontSize = 16.sp
    ),
    val callout: TextStyle = DefaultTextStyle.merge(
        fontFamily = UrbanistRegular,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp
    ),
    val subHeadline: TextStyle = DefaultTextStyle.merge(
        fontFamily = UrbanistRegular,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        lineHeight = 20.sp
    ),
    val subHeadlineMedium: TextStyle = subHeadline.merge(
        fontWeight = FontWeight.Medium,
        fontFamily = UrbanistMedium
    ),
    val subHeadlineSemiBold: TextStyle = subHeadline.merge(
        fontWeight = FontWeight.SemiBold,
        fontFamily = UrbanistSemiBold
    ),
    val footnote: TextStyle = DefaultTextStyle.merge(
        fontFamily = UrbanistRegular,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        lineHeight = 18.sp
    ),
    val footnoteBold: TextStyle = footnote.merge(
        fontWeight = FontWeight.SemiBold,
        fontFamily = UrbanistBold
    ),
    val caption: TextStyle = DefaultTextStyle.merge(
        fontFamily = UrbanistRegular,
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
        lineHeight = 13.sp
    ),
    val captionSemiBold: TextStyle = caption.merge(
        fontWeight = FontWeight.SemiBold,
        fontFamily = UrbanistSemiBold
    ),
    val navigationLabel: TextStyle = DefaultTextStyle.merge(
        fontFamily = UrbanistSemiBold,
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.sp,
        lineHeight = 12.sp
    )
) {

    override fun toString(): String =
        "TrackitTypography(" + "largeTitle=$largeTitle, " + "title1=$title1, " + "title2=$title2, " +
                "title3=$title3, " +
                "headline=$headline, " +
                "body1=$body1, " +
                "body2=$body2, " +
                "body3=$body3, " +
                "body1Medium=$body1Medium, " +
                "body1Bold=$body1Bold, " +
                "callout=$callout, " +
                "subHeadline=$subHeadline, " +
                "subHeadlineMedium=$subHeadlineMedium, " +
                "subHeadlineSemiBold=$subHeadlineSemiBold, " +
                "footnote=$footnote, " +
                "footnoteBold=$footnoteBold, " +
                "caption=$caption, " +
                "captionSemiBold=$captionSemiBold" +
                "navigationLabel=$navigationLabel" +
                ")"
}

internal val DefaultTextStyle = TextStyle.Default.copy(
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    )
)

internal val LocalTypography = staticCompositionLocalOf { TrackitTypography() }
