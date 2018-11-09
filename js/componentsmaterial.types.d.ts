/* generated @ 2018-11-09T15:28:35.563 */
declare namespace ComponentsMaterialTypes {
  interface AppBarLayoutAttributes extends MainTypes.LinearLayoutAttributes {
    android_background?: number;
    android_keyboardNavigationCluster?: boolean;
    android_touchscreenBlocksFocus?: boolean;
    liftOnScroll?: boolean;
  }

  interface AppBarLayoutLayoutParamsAttributes extends MainTypes.LinearLayoutLayoutParamsAttributes {
    layout_scrollFlags?: string;
    layout_scrollInterpolator?: string;
  }

  interface BottomAppBarAttributes extends AppcompatTypes.ToolbarAttributes {
    fabAlignmentMode?: FabAlignmentModeEnum;
    hideOnScroll?: boolean;
  }

  interface BottomNavigationViewAttributes extends MainTypes.FrameLayoutAttributes {
    itemBackground?: string;
    itemIconSize?: string;
    itemIconTint?: number;
    itemTextColor?: number;
    labelVisibilityMode?: LabelVisibilityModeEnum;
  }

  interface ChipAttributes extends AppcompatTypes.AppCompatCheckBoxAttributes {
    chipIconTint?: string;
  }

  interface ChipGroupAttributes extends MainTypes.ViewGroupAttributes {
    chipSpacingHorizontal?: string;
    chipSpacingVertical?: string;
    singleLine?: boolean;
    singleSelection?: boolean;
  }

  interface ChipGroupLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
  }

  interface CircularRevealCardViewAttributes extends CardviewTypes.CardViewAttributes {
  }

  interface CircularRevealCoordinatorLayoutAttributes extends CoordinatorlayoutTypes.CoordinatorLayoutAttributes {
  }

  interface CircularRevealFrameLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  interface CircularRevealLinearLayoutAttributes extends MainTypes.LinearLayoutAttributes {
  }

  interface CircularRevealRelativeLayoutAttributes extends MainTypes.RelativeLayoutAttributes {
  }

  interface CollapsingToolbarLayoutAttributes extends MainTypes.FrameLayoutAttributes {
    collapsedTitleGravity?: string;
    collapsedTitleTextAppearance?: string;
    contentScrim?: string;
    expandedTitleGravity?: string;
    expandedTitleMarginBottom?: string;
    expandedTitleMarginEnd?: string;
    expandedTitleMarginStart?: string;
    expandedTitleMarginTop?: string;
    expandedTitleTextAppearance?: string;
    scrimAnimationDuration?: number;
    scrimVisibleHeightTrigger?: string;
    statusBarScrim?: string;
    title?: string;
    titleEnabled?: boolean;
  }

  interface CollapsingToolbarLayoutLayoutParamsAttributes extends MainTypes.FrameLayoutLayoutParamsAttributes {
    layout_collapseMode?: LayoutCollapseModeEnum;
    layout_collapseParallaxMultiplier?: number;
  }

  enum FabAlignmentModeEnum { 'center', 'end' }

  enum FabSizeEnum { 'auto', 'normal', 'mini' }

  interface FloatingActionButtonAttributes extends MainTypes.ImageButtonAttributes {
    fabCustomSize?: string;
    fabSize?: FabSizeEnum;
    hideMotionSpec?: number;
    hoveredFocusedTranslationZ?: string;
    pressedTranslationZ?: string;
    rippleColor?: string;
    showMotionSpec?: number;
    useCompatPadding?: boolean;
  }

  enum LabelVisibilityModeEnum { 'auto', 'selected', 'labeled', 'unlabeled' }

  enum LayoutCollapseModeEnum { 'none', 'pin', 'parallax' }

  interface MaterialButtonAttributes extends AppcompatTypes.AppCompatButtonAttributes {
    cornerRadius?: string;
    icon?: string;
    iconGravity?: string;
    iconPadding?: string;
    iconSize?: string;
    iconTint?: string;
    iconTintMode?: number;
    rippleColor?: number;
    strokeColor?: number;
    strokeWidth?: number;
  }

  interface MaterialCardViewAttributes extends CardviewTypes.CardViewAttributes {
  }

  interface NavigationViewAttributes extends MainTypes.FrameLayoutAttributes {
    itemBackground?: string;
    itemHorizontalPadding?: string;
    itemIconPadding?: string;
    itemIconTint?: string;
    itemTextColor?: string;
  }

  enum TabGravityEnum { 'fill', 'center' }

  enum TabIndicatorGravityEnum { 'bottom', 'center', 'top', 'stretch' }

  interface TabItemAttributes extends MainTypes.ViewAttributes {
  }

  interface TabLayoutAttributes extends MainTypes.HorizontalScrollViewAttributes {
    tabGravity?: TabGravityEnum;
    tabIndicatorColor?: string;
    tabIndicatorFullWidth?: boolean;
    tabIndicatorGravity?: TabIndicatorGravityEnum;
    tabIndicatorHeight?: string;
    tabInlineLabel?: boolean;
    tabMode?: TabModeEnum;
    tabRippleColor?: string;
    tabUnboundedRipple?: boolean;
  }

  enum TabModeEnum { 'scrollable', 'fixed' }

  interface TextInputEditTextAttributes extends AppcompatTypes.AppCompatEditTextAttributes {
  }

  interface TextInputLayoutAttributes extends MainTypes.LinearLayoutAttributes {
    counterEnabled?: boolean;
    counterMaxLength?: number;
    errorEnabled?: boolean;
    helperTextEnabled?: boolean;
    hintAnimationEnabled?: boolean;
    hintEnabled?: boolean;
    passwordToggleContentDescription?: string;
    passwordToggleDrawable?: string;
    passwordToggleEnabled?: boolean;
  }

  interface TransformationChildCardAttributes extends CircularRevealCardViewAttributes {
  }

  interface TransformationChildLayoutAttributes extends CircularRevealFrameLayoutAttributes {
  }
}
