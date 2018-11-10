/* generated @ 2018-11-10T13:55:20.393 */
/// <reference path='./main.types.d.ts' />
/// <reference path='./cardview.types.d.ts' />
/// <reference path='./appcompat.types.d.ts' />
/// <reference path='./coordinatorlayout.types.d.ts' />

declare namespace ComponentsMaterialTypes {
  export interface AppBarLayoutAttributes extends MainTypes.LinearLayoutAttributes {
    android_background?: number;
    android_keyboardNavigationCluster?: boolean;
    android_touchscreenBlocksFocus?: boolean;
    liftOnScroll?: boolean;
  }

  export interface AppBarLayoutLayoutParamsAttributes extends MainTypes.LinearLayoutLayoutParamsAttributes {
    layout_scrollFlags?: string;
    layout_scrollInterpolator?: string;
  }

  export interface BottomAppBarAttributes extends AppcompatTypes.ToolbarAttributes {
    fabAlignmentMode?: FabAlignmentModeEnum;
    hideOnScroll?: boolean;
  }

  export interface BottomNavigationViewAttributes extends MainTypes.FrameLayoutAttributes {
    itemBackground?: string;
    itemIconSize?: string;
    itemIconTint?: number;
    itemTextColor?: number;
    labelVisibilityMode?: LabelVisibilityModeEnum;
  }

  export interface ChipAttributes extends AppcompatTypes.AppCompatCheckBoxAttributes {
    chipIconTint?: string;
  }

  export interface ChipGroupAttributes extends MainTypes.ViewGroupAttributes {
    chipSpacingHorizontal?: string;
    chipSpacingVertical?: string;
    singleLine?: boolean;
    singleSelection?: boolean;
  }

  export interface ChipGroupLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
  }

  export interface CircularRevealCardViewAttributes extends CardviewTypes.CardViewAttributes {
  }

  export interface CircularRevealCoordinatorLayoutAttributes extends CoordinatorlayoutTypes.CoordinatorLayoutAttributes {
  }

  export interface CircularRevealFrameLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  export interface CircularRevealLinearLayoutAttributes extends MainTypes.LinearLayoutAttributes {
  }

  export interface CircularRevealRelativeLayoutAttributes extends MainTypes.RelativeLayoutAttributes {
  }

  export interface CollapsingToolbarLayoutAttributes extends MainTypes.FrameLayoutAttributes {
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

  export interface CollapsingToolbarLayoutLayoutParamsAttributes extends MainTypes.FrameLayoutLayoutParamsAttributes {
    layout_collapseMode?: LayoutCollapseModeEnum;
    layout_collapseParallaxMultiplier?: number;
  }

  export enum FabAlignmentModeEnum { 'center', 'end' }

  export enum FabSizeEnum { 'auto', 'normal', 'mini' }

  export interface FloatingActionButtonAttributes extends MainTypes.ImageButtonAttributes {
    fabCustomSize?: string;
    fabSize?: FabSizeEnum;
    hideMotionSpec?: number;
    hoveredFocusedTranslationZ?: string;
    pressedTranslationZ?: string;
    rippleColor?: string;
    showMotionSpec?: number;
    useCompatPadding?: boolean;
  }

  export enum LabelVisibilityModeEnum { 'auto', 'selected', 'labeled', 'unlabeled' }

  export enum LayoutCollapseModeEnum { 'none', 'pin', 'parallax' }

  export interface MaterialButtonAttributes extends AppcompatTypes.AppCompatButtonAttributes {
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

  export interface MaterialCardViewAttributes extends CardviewTypes.CardViewAttributes {
  }

  export interface NavigationViewAttributes extends MainTypes.FrameLayoutAttributes {
    itemBackground?: string;
    itemHorizontalPadding?: string;
    itemIconPadding?: string;
    itemIconTint?: string;
    itemTextColor?: string;
  }

  export enum TabGravityEnum { 'fill', 'center' }

  export enum TabIndicatorGravityEnum { 'bottom', 'center', 'top', 'stretch' }

  export interface TabItemAttributes extends MainTypes.ViewAttributes {
  }

  export interface TabLayoutAttributes extends MainTypes.HorizontalScrollViewAttributes {
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

  export enum TabModeEnum { 'scrollable', 'fixed' }

  export interface TextInputEditTextAttributes extends AppcompatTypes.AppCompatEditTextAttributes {
  }

  export interface TextInputLayoutAttributes extends MainTypes.LinearLayoutAttributes {
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

  export interface TransformationChildCardAttributes extends CircularRevealCardViewAttributes {
  }

  export interface TransformationChildLayoutAttributes extends CircularRevealFrameLayoutAttributes {
  }
}
