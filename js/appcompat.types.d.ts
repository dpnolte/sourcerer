/* generated @ 2018-11-10T13:55:13.947 */
/// <reference path='./main.types.d.ts' />

declare namespace AppcompatTypes {
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
    showDividers?: string;
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
    thumbTintMode?: ThumbTintModeEnum_;
    track?: string;
    trackTint?: string;
    trackTintMode?: TrackTintModeEnum_;
  }

  export enum ThumbTintModeEnum_ { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

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

  export enum TrackTintModeEnum_ { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }
}
