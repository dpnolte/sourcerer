/* generated @ 2018-11-05T13:36:11.016 */
import "./generated.attributes";
import "./generated-cardview.attributes";
import "./generated-appcompat.attributes";
import "./generated-coordinatorlayout.attributes";

declare module "LayoutTypes" {
  interface AppBarLayoutAttributes extends LinearLayoutAttributes {
    android_background?: number;
    android_keyboardNavigationCluster?: boolean;
    android_touchscreenBlocksFocus?: boolean;
    liftOnScroll?: boolean;
  }

  interface AppBarLayoutLayoutParamsAttributes extends LinearLayoutLayoutParamsAttributes {
    layout_scrollFlags?: string;
    layout_scrollInterpolator?: string;
  }

  interface BottomAppBarAttributes extends ToolbarAttributes {
    fabAlignmentMode?: FabAlignmentModeEnum;
    hideOnScroll?: boolean;
  }

  interface BottomNavigationViewAttributes extends FrameLayoutAttributes {
    itemBackground?: string;
    itemIconSize?: string;
    itemIconTint?: number;
    itemTextColor?: number;
    labelVisibilityMode?: LabelVisibilityModeEnum;
  }

  interface ChipAttributes extends AppCompatCheckBoxAttributes {
    chipIconTint?: string;
  }

  interface ChipGroupAttributes extends ViewGroupAttributes {
    chipSpacingHorizontal?: string;
    chipSpacingVertical?: string;
    singleLine?: boolean;
    singleSelection?: boolean;
  }

  interface ChipGroupLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
  }

  interface CircularRevealCardViewAttributes extends CardViewAttributes {
  }

  interface CircularRevealCoordinatorLayoutAttributes extends CoordinatorLayoutAttributes {
  }

  interface CircularRevealFrameLayoutAttributes extends FrameLayoutAttributes {
  }

  interface CircularRevealLinearLayoutAttributes extends LinearLayoutAttributes {
  }

  interface CircularRevealRelativeLayoutAttributes extends RelativeLayoutAttributes {
  }

  interface CollapsingToolbarLayoutAttributes extends FrameLayoutAttributes {
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

  interface CollapsingToolbarLayoutLayoutParamsAttributes extends FrameLayoutLayoutParamsAttributes {
    layout_collapseMode?: LayoutCollapseModeEnum;
    layout_collapseParallaxMultiplier?: number;
  }

  enum FabAlignmentModeEnum { 'center', 'end' }

  enum FabSizeEnum { 'auto', 'normal', 'mini' }

  interface FloatingActionButtonAttributes extends ImageButtonAttributes {
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

  interface MaterialButtonAttributes extends AppCompatButtonAttributes {
    cornerRadius?: string;
    icon?: string;
    iconGravity?: FlagsAccumulator;
    iconPadding?: string;
    iconSize?: string;
    iconTint?: string;
    iconTintMode?: number;
    rippleColor?: number;
    strokeColor?: number;
    strokeWidth?: number;
  }

  interface MaterialCardViewAttributes extends CardViewAttributes {
  }

  interface NavigationViewAttributes extends FrameLayoutAttributes {
    itemBackground?: string;
    itemHorizontalPadding?: string;
    itemIconPadding?: string;
    itemIconTint?: string;
    itemTextColor?: string;
  }

  enum TabGravityEnum { 'fill', 'center' }

  enum TabIndicatorGravityEnum { 'bottom', 'center', 'top', 'stretch' }

  interface TabItemAttributes extends ViewAttributes {
  }

  interface TabLayoutAttributes extends HorizontalScrollViewAttributes {
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

  interface TextInputEditTextAttributes extends AppCompatEditTextAttributes {
  }

  interface TextInputLayoutAttributes extends LinearLayoutAttributes {
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
