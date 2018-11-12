import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from "./main";
import { RecyclerviewTypes } from "./recyclerview";
// types
/* generated @ 2018-11-12T14:29:35.966 */
export namespace LeanbackTypes {
  export interface BaseCardViewAttributes extends MainTypes.FrameLayoutAttributes {
    lbcardBackground?: number;
    lbcardForeground?: number;
    lbcardType?: number;
    lbextraVisibility?: number;
    lbinfoVisibility?: number;
  }
  export interface BaseCardViewLayoutParamsAttributes extends MainTypes.FrameLayoutLayoutParamsAttributes {
    lblayout_viewType?: number;
  }
  export interface BaseGridViewAttributes extends RecyclerviewTypes.RecyclerViewAttributes {
    lbandroid_gravity?: number;
    lbandroid_horizontalSpacing?: string;
    lbandroid_verticalSpacing?: string;
  }
  export interface BrowseFrameLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }
  export interface GuidedActionEditTextAttributes extends MainTypes.EditTextAttributes {
  }
  export interface HorizontalGridViewAttributes extends BaseGridViewAttributes {
    lbnumberOfRows?: number;
    lbrowHeight?: number;
  }
  export interface ImageCardViewAttributes extends BaseCardViewAttributes {
    lbinfoAreaBackground?: number;
  }
  export interface ListRowHoverCardViewAttributes extends MainTypes.LinearLayoutAttributes {
  }
  export interface ListRowViewAttributes extends MainTypes.LinearLayoutAttributes {
  }
  export interface PickerAttributes extends MainTypes.FrameLayoutAttributes {
  }
  export interface RowHeaderViewAttributes extends MainTypes.TextViewAttributes {
  }
  export interface SearchBarAttributes extends MainTypes.RelativeLayoutAttributes {
  }
  export interface SearchEditTextAttributes extends MainTypes.EditTextAttributes {
  }
  export interface SearchOrbViewAttributes extends MainTypes.FrameLayoutAttributes {
    lbsearchOrbIcon?: number;
  }
  export interface ShadowOverlayContainerAttributes extends MainTypes.FrameLayoutAttributes {
  }
  export interface SpeechOrbViewAttributes extends SearchOrbViewAttributes {
  }
  export interface TimePickerAttributes extends PickerAttributes {
    lbis24HourFormat?: boolean;
  }
  export interface TitleViewAttributes extends MainTypes.FrameLayoutAttributes {
  }
  export interface VerticalGridViewAttributes extends BaseGridViewAttributes {
    lbcolumnWidth?: number;
    lbnumberOfColumns?: number;
  }
}
// elements
export const ShadowOverlayContainer = (
  attributes?: LeanbackTypes.ShadowOverlayContainerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.ShadowOverlayContainerAttributes, LayoutParamAttributes> => {
  return element('shadowOverlayContainer', attributes, children);
};
export const ListRowHoverCardView = (
  attributes?: LeanbackTypes.ListRowHoverCardViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.ListRowHoverCardViewAttributes, LayoutParamAttributes> => {
  return element('listRowHoverCardView', attributes, children);
};
export const VerticalGridView = (
  attributes?: LeanbackTypes.VerticalGridViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.VerticalGridViewAttributes, LayoutParamAttributes> => {
  return element('verticalGridView', attributes, children);
};
export const SearchOrbView = (
  attributes?: LeanbackTypes.SearchOrbViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.SearchOrbViewAttributes, LayoutParamAttributes> => {
  return element('searchOrbView', attributes, children);
};
export const TitleView = (
  attributes?: LeanbackTypes.TitleViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.TitleViewAttributes, LayoutParamAttributes> => {
  return element('titleView', attributes, children);
};
export const BrowseFrameLayout = (
  attributes?: LeanbackTypes.BrowseFrameLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.BrowseFrameLayoutAttributes, LayoutParamAttributes> => {
  return element('browseFrameLayout', attributes, children);
};
export const SpeechOrbView = (
  attributes?: LeanbackTypes.SpeechOrbViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.SpeechOrbViewAttributes, LayoutParamAttributes> => {
  return element('speechOrbView', attributes, children);
};
export const LeanbackTimePicker = (
  attributes?: LeanbackTypes.TimePickerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.TimePickerAttributes, LayoutParamAttributes> => {
  return element('leanback.timePicker', attributes, children);
};
export const BaseGridView = (
  attributes?: LeanbackTypes.BaseGridViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.BaseGridViewAttributes, LayoutParamAttributes> => {
  return element('baseGridView', attributes, children);
};
export const ImageCardView = (
  attributes?: LeanbackTypes.ImageCardViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.ImageCardViewAttributes, LayoutParamAttributes> => {
  return element('imageCardView', attributes, children);
};
export const ListRowView = (
  attributes?: LeanbackTypes.ListRowViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.ListRowViewAttributes, LayoutParamAttributes> => {
  return element('listRowView', attributes, children);
};
export const SearchBar = (
  attributes?: LeanbackTypes.SearchBarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.SearchBarAttributes, LayoutParamAttributes> => {
  return element('searchBar', attributes, children);
};
export const Picker = (
  attributes?: LeanbackTypes.PickerAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.PickerAttributes, LayoutParamAttributes> => {
  return element('picker', attributes, children);
};
export const BaseCardView = (
  attributes?: LeanbackTypes.BaseCardViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.BaseCardViewAttributes, LayoutParamAttributes> => {
  return element('baseCardView', attributes, children);
};
export const HorizontalGridView = (
  attributes?: LeanbackTypes.HorizontalGridViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.HorizontalGridViewAttributes, LayoutParamAttributes> => {
  return element('horizontalGridView', attributes, children);
};
export const SearchEditText = (
  attributes?: LeanbackTypes.SearchEditTextAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.SearchEditTextAttributes, LayoutParamAttributes> => {
  return element('searchEditText', attributes, children);
};
export const RowHeaderView = (
  attributes?: LeanbackTypes.RowHeaderViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.RowHeaderViewAttributes, LayoutParamAttributes> => {
  return element('rowHeaderView', attributes, children);
};
export const GuidedActionEditText = (
  attributes?: LeanbackTypes.GuidedActionEditTextAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<LeanbackTypes.GuidedActionEditTextAttributes, LayoutParamAttributes> => {
  return element('guidedActionEditText', attributes, children);
};