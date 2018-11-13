import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from './main';
import { CardviewTypes } from './cardview';
import { AppcompatTypes } from './appcompat';
import { CoordinatorlayoutTypes } from './coordinatorlayout';
// types
/* generated @ 2018-11-13T17:10:57.543 */
export namespace ComponentsMaterialTypes {
  export interface AppBarLayoutAttributes extends MainTypes.LinearLayoutAttributes {
    android_background?: number;
    android_keyboardNavigationCluster?: boolean;
    android_touchscreenBlocksFocus?: boolean;
    liftOnScroll?: boolean;
  }
  export interface AppBarLayoutLayoutParamsAttributes extends MainTypes.LinearLayoutLayoutParamsAttributes {
    layout_scrollFlags?: LayoutScrollFlagsFlagsEnum[];
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
  export enum CollapsedTitleGravityFlagsEnum {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    end = 'end',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export interface CollapsingToolbarLayoutAttributes extends MainTypes.FrameLayoutAttributes {
    collapsedTitleGravity?: CollapsedTitleGravityFlagsEnum[];
    collapsedTitleTextAppearance?: string;
    contentScrim?: string;
    expandedTitleGravity?: ExpandedTitleGravityFlagsEnum[];
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
  export enum ExpandedTitleGravityFlagsEnum {
    bottom = 'bottom',
    center = 'center',
    center_horizontal = 'center_horizontal',
    center_vertical = 'center_vertical',
    end = 'end',
    fill_vertical = 'fill_vertical',
    left = 'left',
    right = 'right',
    start = 'start',
    top = 'top'
  }
  export enum FabAlignmentModeEnum {
    center = 'center',
    end = 'end'
  }
  export enum FabSizeEnum {
    auto = 'auto',
    mini = 'mini',
    normal = 'normal'
  }
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
  export enum IconGravityFlagsEnum {
    start = 'start',
    textStart = 'textStart'
  }
  export enum LabelVisibilityModeEnum {
    auto = 'auto',
    labeled = 'labeled',
    selected = 'selected',
    unlabeled = 'unlabeled'
  }
  export enum LayoutCollapseModeEnum {
    none = 'none',
    parallax = 'parallax',
    pin = 'pin'
  }
  export enum LayoutScrollFlagsFlagsEnum {
    enterAlways = 'enterAlways',
    enterAlwaysCollapsed = 'enterAlwaysCollapsed',
    exitUntilCollapsed = 'exitUntilCollapsed',
    scroll = 'scroll',
    snap = 'snap',
    snapMargins = 'snapMargins'
  }
  export interface MaterialButtonAttributes extends AppcompatTypes.AppCompatButtonAttributes {
    cornerRadius?: string;
    icon?: string;
    iconGravity?: number | IconGravityFlagsEnum[];
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
  export enum TabGravityEnum {
    center = 'center',
    fill = 'fill'
  }
  export enum TabIndicatorGravityEnum {
    bottom = 'bottom',
    center = 'center',
    stretch = 'stretch',
    top = 'top'
  }
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
  export enum TabModeEnum {
    fixed = 'fixed',
    scrollable = 'scrollable'
  }
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
// elements
export const TabLayout = (
  attributes?: ComponentsMaterialTypes.TabLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.TabLayoutAttributes, LayoutParamAttributes> => {
  return element('tabLayout', attributes, children);
};
export const BottomNavigationView = (
  attributes?: ComponentsMaterialTypes.BottomNavigationViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.BottomNavigationViewAttributes, LayoutParamAttributes> => {
  return element('bottomNavigationView', attributes, children);
};
export const CircularRevealCardView = (
  attributes?: ComponentsMaterialTypes.CircularRevealCardViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.CircularRevealCardViewAttributes, LayoutParamAttributes> => {
  return element('circularRevealCardView', attributes, children);
};
export const TextInputLayout = (
  attributes?: ComponentsMaterialTypes.TextInputLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.TextInputLayoutAttributes, LayoutParamAttributes> => {
  return element('textInputLayout', attributes, children);
};
export const CircularRevealFrameLayout = (
  attributes?: ComponentsMaterialTypes.CircularRevealFrameLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.CircularRevealFrameLayoutAttributes, LayoutParamAttributes> => {
  return element('circularRevealFrameLayout', attributes, children);
};
export const TransformationChildLayout = (
  attributes?: ComponentsMaterialTypes.TransformationChildLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.TransformationChildLayoutAttributes, LayoutParamAttributes> => {
  return element('transformationChildLayout', attributes, children);
};
export const ChipGroup = (
  attributes?: ComponentsMaterialTypes.ChipGroupAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.ChipGroupAttributes, LayoutParamAttributes> => {
  return element('chipGroup', attributes, children);
};
export const NavigationView = (
  attributes?: ComponentsMaterialTypes.NavigationViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.NavigationViewAttributes, LayoutParamAttributes> => {
  return element('navigationView', attributes, children);
};
export const CircularRevealLinearLayout = (
  attributes?: ComponentsMaterialTypes.CircularRevealLinearLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.CircularRevealLinearLayoutAttributes, LayoutParamAttributes> => {
  return element('circularRevealLinearLayout', attributes, children);
};
export const AppBarLayout = (
  attributes?: ComponentsMaterialTypes.AppBarLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.AppBarLayoutAttributes, LayoutParamAttributes> => {
  return element('appBarLayout', attributes, children);
};
export const CircularRevealRelativeLayout = (
  attributes?: ComponentsMaterialTypes.CircularRevealRelativeLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.CircularRevealRelativeLayoutAttributes, LayoutParamAttributes> => {
  return element('circularRevealRelativeLayout', attributes, children);
};
export const CircularRevealCoordinatorLayout = (
  attributes?: ComponentsMaterialTypes.CircularRevealCoordinatorLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.CircularRevealCoordinatorLayoutAttributes, LayoutParamAttributes> => {
  return element('circularRevealCoordinatorLayout', attributes, children);
};
export const TransformationChildCard = (
  attributes?: ComponentsMaterialTypes.TransformationChildCardAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.TransformationChildCardAttributes, LayoutParamAttributes> => {
  return element('transformationChildCard', attributes, children);
};
export const BottomAppBar = (
  attributes?: ComponentsMaterialTypes.BottomAppBarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.BottomAppBarAttributes, LayoutParamAttributes> => {
  return element('bottomAppBar', attributes, children);
};
export const CollapsingToolbarLayout = (
  attributes?: ComponentsMaterialTypes.CollapsingToolbarLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.CollapsingToolbarLayoutAttributes, LayoutParamAttributes> => {
  return element('collapsingToolbarLayout', attributes, children);
};
export const MaterialCardView = (
  attributes?: ComponentsMaterialTypes.MaterialCardViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.MaterialCardViewAttributes, LayoutParamAttributes> => {
  return element('materialCardView', attributes, children);
};
export const TextInputEditText = (
  attributes?: ComponentsMaterialTypes.TextInputEditTextAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.TextInputEditTextAttributes, LayoutParamAttributes> => {
  return element('textInputEditText', attributes, children);
};
export const TabItem = (
  attributes?: ComponentsMaterialTypes.TabItemAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.TabItemAttributes, LayoutParamAttributes> => {
  return element('tabItem', attributes, children);
};
export const Chip = (
  attributes?: ComponentsMaterialTypes.ChipAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.ChipAttributes, LayoutParamAttributes> => {
  return element('chip', attributes, children);
};
export const FloatingActionButton = (
  attributes?: ComponentsMaterialTypes.FloatingActionButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.FloatingActionButtonAttributes, LayoutParamAttributes> => {
  return element('floatingActionButton', attributes, children);
};
export const MaterialButton = (
  attributes?: ComponentsMaterialTypes.MaterialButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<ComponentsMaterialTypes.MaterialButtonAttributes, LayoutParamAttributes> => {
  return element('materialButton', attributes, children);
};
