package com.laidpack.sourcerer.generated.material.components

import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.circularreveal.CircularRevealFrameLayout
import com.google.android.material.circularreveal.CircularRevealLinearLayout
import com.google.android.material.circularreveal.CircularRevealRelativeLayout
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.google.android.material.circularreveal.coordinatorlayout.CircularRevealCoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.transformation.TransformationChildCard
import com.google.android.material.transformation.TransformationChildLayout
import com.laidpack.sourcerer.services.InflaterComponent
import com.laidpack.sourcerer.services.SerializerComponent
import com.laidpack.sourcerer.services.api.Bootstrapper
import com.squareup.moshi.Moshi

class GeneratedMaterialComponentsBootstrapper : Bootstrapper {
    override fun bootstrap(serializer: SerializerComponent, inflater: InflaterComponent) {
        serializer.registerAutoGeneratedAdapter(TabLayout::class, {moshi -> TabLayoutAttributesJsonAdapter(moshi as Moshi)}, "tabLayout")
        serializer.registerAutoGeneratedAdapter(TabItem::class, {moshi -> TabItemAttributesJsonAdapter(moshi as Moshi)}, "tabItem")
        serializer.registerAutoGeneratedAdapter(CollapsingToolbarLayout::class, {moshi -> CollapsingToolbarLayoutAttributesJsonAdapter(moshi as Moshi)}, "collapsingToolbarLayout")
        serializer.registerAutoGeneratedAdapter(CollapsingToolbarLayout.LayoutParams::class, {moshi -> CollapsingToolbarLayoutLayoutParamsAttributesJsonAdapter(moshi as Moshi)}, "com.google.android.material.appbar.CollapsingToolbarLayout.LayoutParams")
        serializer.registerAutoGeneratedAdapter(AppBarLayout::class, {moshi -> AppBarLayoutAttributesJsonAdapter(moshi as Moshi)}, "appBarLayout")
        serializer.registerAutoGeneratedAdapter(AppBarLayout.LayoutParams::class, {moshi -> AppBarLayoutLayoutParamsAttributesJsonAdapter(moshi as Moshi)}, "com.google.android.material.appbar.AppBarLayout.LayoutParams")
        serializer.registerAutoGeneratedAdapter(FloatingActionButton::class, {moshi -> FloatingActionButtonAttributesJsonAdapter(moshi as Moshi)}, "floatingActionButton")
        serializer.registerAutoGeneratedAdapter(MaterialCardView::class, {moshi -> MaterialCardViewAttributesJsonAdapter(moshi as Moshi)}, "materialCardView")
        serializer.registerAutoGeneratedAdapter(BottomAppBar::class, {moshi -> BottomAppBarAttributesJsonAdapter(moshi as Moshi)}, "bottomAppBar")
        serializer.registerAutoGeneratedAdapter(CircularRevealFrameLayout::class, {moshi -> CircularRevealFrameLayoutAttributesJsonAdapter(moshi as Moshi)}, "circularRevealFrameLayout")
        serializer.registerAutoGeneratedAdapter(TransformationChildLayout::class, {moshi -> TransformationChildLayoutAttributesJsonAdapter(moshi as Moshi)}, "transformationChildLayout")
        serializer.registerAutoGeneratedAdapter(CircularRevealCardView::class, {moshi -> CircularRevealCardViewAttributesJsonAdapter(moshi as Moshi)}, "circularRevealCardView")
        serializer.registerAutoGeneratedAdapter(TransformationChildCard::class, {moshi -> TransformationChildCardAttributesJsonAdapter(moshi as Moshi)}, "transformationChildCard")
        serializer.registerAutoGeneratedAdapter(ChipGroup::class, {moshi -> ChipGroupAttributesJsonAdapter(moshi as Moshi)}, "chipGroup")
        serializer.registerAutoGeneratedAdapter(ChipGroup.LayoutParams::class, {moshi -> ChipGroupLayoutParamsAttributesJsonAdapter(moshi as Moshi)}, "com.google.android.material.chip.ChipGroup.LayoutParams")
        serializer.registerAutoGeneratedAdapter(Chip::class, {moshi -> ChipAttributesJsonAdapter(moshi as Moshi)}, "chip")
        serializer.registerAutoGeneratedAdapter(NavigationView::class, {moshi -> NavigationViewAttributesJsonAdapter(moshi as Moshi)}, "navigationView")
        serializer.registerAutoGeneratedAdapter(TextInputEditText::class, {moshi -> TextInputEditTextAttributesJsonAdapter(moshi as Moshi)}, "textInputEditText")
        serializer.registerAutoGeneratedAdapter(TextInputLayout::class, {moshi -> TextInputLayoutAttributesJsonAdapter(moshi as Moshi)}, "textInputLayout")
        serializer.registerAutoGeneratedAdapter(CircularRevealRelativeLayout::class, {moshi -> CircularRevealRelativeLayoutAttributesJsonAdapter(moshi as Moshi)}, "circularRevealRelativeLayout")
        serializer.registerAutoGeneratedAdapter(CircularRevealLinearLayout::class, {moshi -> CircularRevealLinearLayoutAttributesJsonAdapter(moshi as Moshi)}, "circularRevealLinearLayout")
        serializer.registerAutoGeneratedAdapter(CircularRevealCoordinatorLayout::class, {moshi -> CircularRevealCoordinatorLayoutAttributesJsonAdapter(moshi as Moshi)}, "circularRevealCoordinatorLayout")
        serializer.registerAutoGeneratedAdapter(MaterialButton::class, {moshi -> MaterialButtonAttributesJsonAdapter(moshi as Moshi)}, "materialButton")
        serializer.registerAutoGeneratedAdapter(BottomNavigationView::class, {moshi -> BottomNavigationViewAttributesJsonAdapter(moshi as Moshi)}, "bottomNavigationView")
        serializer.associateViewGroupWithLayoutParams("tabLayout", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("collapsingToolbarLayout", "com.google.android.material.appbar.CollapsingToolbarLayout.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("appBarLayout", "com.google.android.material.appbar.AppBarLayout.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("materialCardView", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("bottomAppBar", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("circularRevealFrameLayout", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("transformationChildLayout", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("circularRevealCardView", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("transformationChildCard", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("chipGroup", "com.google.android.material.chip.ChipGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("navigationView", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("textInputLayout", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("circularRevealRelativeLayout", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("circularRevealLinearLayout", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("circularRevealCoordinatorLayout", "android.view.ViewGroup.LayoutParams")
        serializer.associateViewGroupWithLayoutParams("bottomNavigationView", "android.view.ViewGroup.LayoutParams")
        inflater.addFactory(TabLayoutFactory<TabLayout, TabLayoutAttributes>())
        inflater.addFactory(TabItemFactory<TabItem, TabItemAttributes>())
        inflater.addFactory(CollapsingToolbarLayoutFactory<CollapsingToolbarLayout, CollapsingToolbarLayoutAttributes>())
        inflater.addFactory(CollapsingToolbarLayoutLayoutParamsFactory<CollapsingToolbarLayout.LayoutParams, CollapsingToolbarLayoutLayoutParamsAttributes>())
        inflater.addFactory(AppBarLayoutFactory<AppBarLayout, AppBarLayoutAttributes>())
        inflater.addFactory(AppBarLayoutLayoutParamsFactory<AppBarLayout.LayoutParams, AppBarLayoutLayoutParamsAttributes>())
        inflater.addFactory(FloatingActionButtonFactory<FloatingActionButton, FloatingActionButtonAttributes>())
        inflater.addFactory(MaterialCardViewFactory<MaterialCardView, MaterialCardViewAttributes>())
        inflater.addFactory(BottomAppBarFactory<BottomAppBar, BottomAppBarAttributes>())
        inflater.addFactory(CircularRevealFrameLayoutFactory<CircularRevealFrameLayout, CircularRevealFrameLayoutAttributes>())
        inflater.addFactory(TransformationChildLayoutFactory<TransformationChildLayout, TransformationChildLayoutAttributes>())
        inflater.addFactory(CircularRevealCardViewFactory<CircularRevealCardView, CircularRevealCardViewAttributes>())
        inflater.addFactory(TransformationChildCardFactory<TransformationChildCard, TransformationChildCardAttributes>())
        inflater.addFactory(ChipGroupFactory<ChipGroup, ChipGroupAttributes>())
        inflater.addFactory(ChipGroupLayoutParamsFactory<ChipGroup.LayoutParams, ChipGroupLayoutParamsAttributes>())
        inflater.addFactory(ChipFactory<Chip, ChipAttributes>())
        inflater.addFactory(NavigationViewFactory<NavigationView, NavigationViewAttributes>())
        inflater.addFactory(TextInputEditTextFactory<TextInputEditText, TextInputEditTextAttributes>())
        inflater.addFactory(TextInputLayoutFactory<TextInputLayout, TextInputLayoutAttributes>())
        inflater.addFactory(CircularRevealRelativeLayoutFactory<CircularRevealRelativeLayout, CircularRevealRelativeLayoutAttributes>())
        inflater.addFactory(CircularRevealLinearLayoutFactory<CircularRevealLinearLayout, CircularRevealLinearLayoutAttributes>())
        inflater.addFactory(CircularRevealCoordinatorLayoutFactory<CircularRevealCoordinatorLayout, CircularRevealCoordinatorLayoutAttributes>())
        inflater.addFactory(MaterialButtonFactory<MaterialButton, MaterialButtonAttributes>())
        inflater.addFactory(BottomNavigationViewFactory<BottomNavigationView, BottomNavigationViewAttributes>())
    }
}
