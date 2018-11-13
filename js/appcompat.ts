import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from './main';
// types
/* generated @ 2018-11-13T17:52:38.671 */
export namespace AppcompatTypes {
  export interface ActionMenuViewAttributes extends LinearLayoutCompatAttributes {
  }
  export interface ActionMenuViewLayoutParamsAttributes extends LinearLayoutCompatLayoutParamsAttributes {
  }
  export interface AppCompatAutoCompleteTextViewAttributes extends MainTypes.AutoCompleteTextViewAttributes {
    popupBackground?: number;
  }
  export interface AppCompatButtonAttributes extends MainTypes.ButtonAttributes {
  }
  export interface AppCompatCheckBoxAttributes extends MainTypes.CheckBoxAttributes {
  }
  export interface AppCompatCheckedTextViewAttributes extends MainTypes.CheckedTextViewAttributes {
  }
  export interface AppCompatEditTextAttributes extends MainTypes.EditTextAttributes {
  }
  export interface AppCompatImageButtonAttributes extends MainTypes.ImageButtonAttributes {
  }
  export interface AppCompatImageViewAttributes extends MainTypes.ImageViewAttributes {
  }
  export interface AppCompatMultiAutoCompleteTextViewAttributes extends MainTypes.MultiAutoCompleteTextViewAttributes {
    popupBackground?: number;
  }
  export interface AppCompatRadioButtonAttributes extends MainTypes.RadioButtonAttributes {
  }
  export interface AppCompatRatingBarAttributes extends MainTypes.RatingBarAttributes {
  }
  export interface AppCompatSeekBarAttributes extends MainTypes.SeekBarAttributes {
  }
  export interface AppCompatSpinnerAttributes extends MainTypes.SpinnerAttributes {
    Spinner_android_dropDownWidth?: number;
  }
  export interface AppCompatTextViewAttributes extends MainTypes.TextViewAttributes {
  }
  export interface LinearLayoutCompatAttributes extends MainTypes.ViewGroupAttributes {
    android_baselineAligned?: boolean;
    android_baselineAlignedChildIndex?: number;
    android_gravity?: number;
    android_orientation?: number;
    divider?: number;
    dividerPadding?: string;
    measureWithLargestChild?: boolean;
    showDividers?: ShowDividersFlagsEnum[];
  }
  export interface LinearLayoutCompatLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
    android_layout_gravity?: number;
    android_layout_weight?: number;
  }
  export interface SearchViewAttributes extends LinearLayoutCompatAttributes {
    android_focusable?: boolean;
    android_imeOptions?: number;
    android_inputType?: number;
    android_maxWidth?: string;
    iconifiedByDefault?: boolean;
    queryHint?: string;
  }
  export enum ShowDividersFlagsEnum {
    beginning = 'beginning',
    end = 'end',
    middle = 'middle',
    none = 'none'
  }
  export interface SwitchCompatAttributes extends MainTypes.CompoundButtonAttributes {
    android_textOff?: string;
    android_textOn?: string;
    android_thumb?: number;
    showText?: boolean;
    splitTrack?: boolean;
    switchMinWidth?: string;
    switchPadding?: string;
    switchTextAppearance?: string;
    thumbTextPadding?: string;
    thumbTint?: string;
    thumbTintMode?: ThumbTintModeEnum;
    track?: string;
    trackTint?: string;
    trackTintMode?: TrackTintModeEnum;
  }
  export enum ThumbTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
  export interface ToolbarAttributes extends MainTypes.ViewGroupAttributes {
    contentInsetEnd?: string;
    contentInsetEndWithActions?: string;
    contentInsetLeft?: string;
    contentInsetRight?: string;
    contentInsetStart?: string;
    contentInsetStartWithNavigation?: string;
    logo?: number;
    logoDescription?: string;
    navigationContentDescription?: string;
    navigationIcon?: string;
    popupTheme?: number;
    subtitle?: string;
    subtitleTextColor?: string;
    title?: string;
    titleMarginBottom?: string;
    titleMarginEnd?: string;
    titleMarginStart?: string;
    titleMarginTop?: string;
    titleTextColor?: string;
  }
  export interface ToolbarLayoutParamsAttributes extends MainTypes.ViewGroupLayoutParamsAttributes {
  }
  export enum TrackTintModeEnum {
    add = 'add',
    multiply = 'multiply',
    screen = 'screen',
    src_atop = 'src_atop',
    src_in = 'src_in',
    src_over = 'src_over'
  }
}
// elements
export const AppcompatSearchView = (
  attributes?: AppcompatTypes.SearchViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.SearchViewAttributes, LayoutParamAttributes> => {
  return element('appcompat.searchView', attributes, children);
};
export const LinearLayoutCompat = (
  attributes?: AppcompatTypes.LinearLayoutCompatAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.LinearLayoutCompatAttributes, LayoutParamAttributes> => {
  return element('linearLayoutCompat', attributes, children);
};
export const AppcompatToolbar = (
  attributes?: AppcompatTypes.ToolbarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.ToolbarAttributes, LayoutParamAttributes> => {
  return element('appcompat.toolbar', attributes, children);
};
export const AppcompatActionMenuView = (
  attributes?: AppcompatTypes.ActionMenuViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.ActionMenuViewAttributes, LayoutParamAttributes> => {
  return element('appcompat.actionMenuView', attributes, children);
};
export const AppCompatSpinner = (
  attributes?: AppcompatTypes.AppCompatSpinnerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatSpinnerAttributes, LayoutParamAttributes> => {
  return element('appCompatSpinner', attributes, children);
};
export const AppCompatImageView = (
  attributes?: AppcompatTypes.AppCompatImageViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatImageViewAttributes, LayoutParamAttributes> => {
  return element('appCompatImageView', attributes, children);
};
export const AppCompatRatingBar = (
  attributes?: AppcompatTypes.AppCompatRatingBarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatRatingBarAttributes, LayoutParamAttributes> => {
  return element('appCompatRatingBar', attributes, children);
};
export const AppCompatTextView = (
  attributes?: AppcompatTypes.AppCompatTextViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatTextViewAttributes, LayoutParamAttributes> => {
  return element('appCompatTextView', attributes, children);
};
export const AppCompatSeekBar = (
  attributes?: AppcompatTypes.AppCompatSeekBarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatSeekBarAttributes, LayoutParamAttributes> => {
  return element('appCompatSeekBar', attributes, children);
};
export const AppCompatAutoCompleteTextView = (
  attributes?: AppcompatTypes.AppCompatAutoCompleteTextViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatAutoCompleteTextViewAttributes, LayoutParamAttributes> => {
  return element('appCompatAutoCompleteTextView', attributes, children);
};
export const AppCompatRadioButton = (
  attributes?: AppcompatTypes.AppCompatRadioButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatRadioButtonAttributes, LayoutParamAttributes> => {
  return element('appCompatRadioButton', attributes, children);
};
export const SwitchCompat = (
  attributes?: AppcompatTypes.SwitchCompatAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.SwitchCompatAttributes, LayoutParamAttributes> => {
  return element('switchCompat', attributes, children);
};
export const AppCompatImageButton = (
  attributes?: AppcompatTypes.AppCompatImageButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatImageButtonAttributes, LayoutParamAttributes> => {
  return element('appCompatImageButton', attributes, children);
};
export const AppCompatEditText = (
  attributes?: AppcompatTypes.AppCompatEditTextAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatEditTextAttributes, LayoutParamAttributes> => {
  return element('appCompatEditText', attributes, children);
};
export const AppCompatButton = (
  attributes?: AppcompatTypes.AppCompatButtonAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatButtonAttributes, LayoutParamAttributes> => {
  return element('appCompatButton', attributes, children);
};
export const AppCompatCheckedTextView = (
  attributes?: AppcompatTypes.AppCompatCheckedTextViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatCheckedTextViewAttributes, LayoutParamAttributes> => {
  return element('appCompatCheckedTextView', attributes, children);
};
export const AppCompatMultiAutoCompleteTextView = (
  attributes?: AppcompatTypes.AppCompatMultiAutoCompleteTextViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatMultiAutoCompleteTextViewAttributes, LayoutParamAttributes> => {
  return element('appCompatMultiAutoCompleteTextView', attributes, children);
};
export const AppCompatCheckBox = (
  attributes?: AppcompatTypes.AppCompatCheckBoxAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<AppcompatTypes.AppCompatCheckBoxAttributes, LayoutParamAttributes> => {
  return element('appCompatCheckBox', attributes, children);
};
