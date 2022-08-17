package com.mobilejazz.wildhike.extension

import androidx.annotation.DrawableRes
import androidx.compose.ui.test.SemanticsMatcher
import com.mobilejazz.compose.semantics.SemanticPropDrawableId

fun hasDrawable(@DrawableRes id: Int): SemanticsMatcher =
    SemanticsMatcher.expectValue(SemanticPropDrawableId, id)